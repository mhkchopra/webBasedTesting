import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;

public class TestCase2 extends BaseClass {
        public static void main(String[] args)   {
            initializeWebDriver();
            try {
                {
                    // Perform actions with the WebDriver
                    driver.get("http://jupiter.cloud.planittesting.com");

                    driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Maximum wait time of 15 seconds
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/div/a")));


                    element.click();

                    System.out.println("Mandatory fields are : ");


                    //Defining excel path
                    String excelFilePath = ".\\dataFile\\contactSheet.xlsx";
                    FileInputStream Inputstream=new FileInputStream(excelFilePath);
//                Workbook excel = WorkbookFactory.create(Inputstream, "default");

                    //fetching workbook using class XSSFWorkbook
                    XSSFWorkbook workbook=new XSSFWorkbook(Inputstream);
                    XSSFSheet sheet= workbook.getSheet("Sheet1");

                    //Reading rows and column
                    int rows=sheet.getLastRowNum();
                    int cols=sheet.getRow(1).getLastCellNum();
//                Thread.sleep(5000);

                    for(int r=2;r<=rows;r++)
                    {
                        XSSFRow row=sheet.getRow(r);
                        String forename=row.getCell(0).getStringCellValue();
                        String surname=row.getCell(1).getStringCellValue();
                        String email=row.getCell(2).getStringCellValue();
                        String telephone=row.getCell(3).getStringCellValue();
                        String message=row.getCell(4).getStringCellValue();

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
                        Thread.sleep(2000);

                        System.out.println("Test 2.4 -Successful submission: " + driver.equals(driver.findElement(By.xpath("/html/body/div[2]/div/div"))));

                        back.click();




                    }

                    System.out.println("Test case 1.5 - Validating Errors are gone");
                    //Close the browser and quit the WebDriver
                    driver.quit();


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

