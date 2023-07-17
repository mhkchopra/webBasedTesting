import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;

public class TestCase2 extends BaseClass {

    @Test
    public void testMethod2() {
        initializeWebDriver();
        try {
            {
                // Perform actions with the WebDriver
                driver.get("http://jupiter.cloud.planittesting.com");

                driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();

                Thread.sleep(2000);

                //Populating Mandatory fields
                System.out.println("Test case 2.2 Mandatory fields are : ");
                String mandatoryField1 = driver.findElement(By.xpath("/html/body/div[2]/div/form/fieldset/div[1]/label")).getText();
                String mandatoryField2 = driver.findElement(By.xpath("/html/body/div[2]/div/form/fieldset/div[2]/label")).getText();
                String mandatoryField3 = driver.findElement(By.xpath("/html/body/div[2]/div/form/fieldset/div[3]/label")).getText();
                System.out.println(mandatoryField1);
                System.out.println(mandatoryField2);
                System.out.println(mandatoryField3);

                //Defining excel path
                String excelFilePath = ".\\dataFile\\contactSheet.xlsx";
                FileInputStream Inputstream = new FileInputStream(excelFilePath);

                //fetching workbook from Execl data using class XSSFWorkbook
                XSSFWorkbook workbook = new XSSFWorkbook(Inputstream);
                XSSFSheet sheet = workbook.getSheet("Sheet1");


                //Reading rows
                int rows = sheet.getLastRowNum();
                Thread.sleep(2000);

                for (int r = 1; r <= rows; r++) {
                    XSSFRow row = sheet.getRow(r);
                    String forename = row.getCell(0).getStringCellValue();
                    String surname = row.getCell(1).getStringCellValue();
                    String email = row.getCell(2).getStringCellValue();
                    String telephone = row.getCell(3).getStringCellValue();
                    String message = row.getCell(4).getStringCellValue();

                    Thread.sleep(3000);
                    //Filling Contact form
                    driver.findElement(By.xpath("//*[@id=\"forename\"]")).sendKeys(forename);
                    driver.findElement(By.xpath("//*[@id=\"surname\"]")).sendKeys(surname);
                    driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
                    driver.findElement(By.xpath("//*[@id=\"telephone\"]")).sendKeys(telephone);
                    driver.findElement(By.xpath("//*[@id=\"message\"]")).sendKeys(message);

                    WebDriverWait waitForFeed = new WebDriverWait(driver, Duration.ofSeconds(50)); // Maximum wait time of 60 seconds
                    WebElement submit = waitForFeed.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/div/a")));
                    Thread.sleep(1000);
                    submit.click();

                    WebDriverWait waitForFeedback = new WebDriverWait(driver, Duration.ofSeconds(60)); // Maximum wait time of 100 seconds
                    WebElement back = waitForFeedback.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/a")));
                    Thread.sleep(1000);
                    WebElement success = driver.findElement(By.xpath("/html/body/div[2]/div/div"));
                    //Validating the actual and expected text
                    String expectedSuccessText = success.getText();
                    System.out.println(expectedSuccessText);

                    //Validating if success message contains below text
                    Assert.assertTrue(expectedSuccessText.contains("we appreciate your feedback"));

                    back.click();

                }
                //Close the browser and quit the WebDriver
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

