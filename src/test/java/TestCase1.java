import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.io.FileInputStream;
import java.time.Duration;

public class TestCase1 extends BaseClass {
    public static void main(String[] args) {
        initializeWebDriver();

        try {
            {

                // Perform actions with the WebDriver
                driver.get("http://jupiter.cloud.planittesting.com");
                System.out.println("Page title: " + driver.getTitle());

                driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Maximum wait time of 15 seconds
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/div/a")));


                element.click();


                WebElement forenameErrMessage = driver.findElement(By.xpath("//*[@id=\"forename-err\"]"));

                //Validating the actual and expected text
                String actualErrorText = "Forename is required";
                String expectedErrorText = forenameErrMessage.getText();

                Assert.assertEquals(actualErrorText, expectedErrorText);

                System.out.println("Test case 1.3.1 Successfully Verified error message for Field Forename");


                WebElement emailErrMessage = driver.findElement(By.xpath("//*[@id=\"email-err\"]"));

                //Validating the actual and expected text
                String expectedEmailErrorText = "Email is required";

                String actualEmailErrorText = emailErrMessage.getText();

                Assert.assertEquals(actualEmailErrorText, expectedEmailErrorText);

                System.out.println("Test case 1.3.2 Successfully Verified error message for Field Email");


                WebElement messageErrMessage = driver.findElement(By.xpath("//*[@id=\"message-err\"]"));

                //Validating the actual and expected text
                String expectedMessageErrorText = "Message is required";
                String actualMessageErrorText = messageErrMessage.getText();


                Assert.assertEquals(actualMessageErrorText, expectedMessageErrorText);

                System.out.println("Test case 1.3.3 Successfully Verified error message for Field Message");


                //Defining excel path
                String excelFilePath = ".\\dataFile\\contactSheet.xlsx";
                FileInputStream Inputstream = new FileInputStream(excelFilePath);

                //fetching workbook using class XSSFWorkbook
                XSSFWorkbook workbook = new XSSFWorkbook(Inputstream);
                XSSFSheet sheet = workbook.getSheet("Sheet1");

                //Reading rows a
                int rows = sheet.getLastRowNum();

                for (int r = 4; r <= rows; r++) {
                    XSSFRow row = sheet.getRow(r);
                    String forename = row.getCell(0).getStringCellValue();
                    String surname = row.getCell(1).getStringCellValue();
                    String email = row.getCell(2).getStringCellValue();
                    String telephone = row.getCell(3).getStringCellValue();
                    String message = row.getCell(4).getStringCellValue();

                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[@id=\"forename\"]")).sendKeys(forename);
                    driver.findElement(By.xpath("//*[@id=\"surname\"]")).sendKeys(surname);
                    driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
                    driver.findElement(By.xpath("//*[@id=\"telephone\"]")).sendKeys(telephone);
                    driver.findElement(By.xpath("//*[@id=\"message\"]")).sendKeys(message);

                    Thread.sleep(3000);
                    driver.findElement(By.xpath("/html/body/div[2]/div/form/div/a")).click();

                    WebDriverWait waitForFeedback = new WebDriverWait(driver, Duration.ofSeconds(15)); // Maximum wait time of 8 seconds
                    WebElement back = waitForFeedback.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/a")));

                    back.click();

                }

                System.out.println("Test case 1.5 - With success submission, Validating Errors are gone");

                //Close the browser and quit the WebDriver
                driver.quit();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
