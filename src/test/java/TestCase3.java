import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TestCase3 extends BaseClass {

    @Test
    public void testMethod3() {
        initializeWebDriver();

        try {
            {
                // Perform actions with the WebDriver
                driver.get("https://jupiter.cloud.planittesting.com/#/shop");

                //Buying 2 Stuffed frog
                driver.findElement(By.xpath("//*[@id=\"product-2\"]/div/p/a")).click();
                driver.findElement(By.xpath("//*[@id=\"product-2\"]/div/p/a")).click();

                //Buying 5 Floffy Bunny
                WebElement floffyBunny = driver.findElement(By.xpath("//*[@id=\"product-4\"]/div/p/a"));

                int numberOfClicks = 5; // Number of times to click the element

                for (int i = 0; i < numberOfClicks; i++) {
                    ((WebElement) floffyBunny).click();
                    Thread.sleep(1000); // Wait for 1 second before the next click
                }

                //Buying 3 Valentine Bear
                driver.findElement(By.xpath("//*[@id=\"product-7\"]/div/p/a")).click();
                driver.findElement(By.xpath("//*[@id=\"product-7\"]/div/p/a")).click();
                driver.findElement(By.xpath("//*[@id=\"product-7\"]/div/p/a")).click();

                //Going to cart page
                driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
                Thread.sleep(1000);

                //Finding Sub-total elements of each product
                WebElement stuffedBunnySubTotal = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[4]"));
                WebElement fluffyBunnySubTotal = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[4]"));
                WebElement valentineBearSubTotal = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[3]/td[4]"));


                //Expected subtotal Values
                Double subTotalSF = 21.98;
                Double subTotalFB = 49.95;
                Double subTotalVB = 44.97;

                //Validating the actual and expected subtotal
                String expectedSubTotalSF = stuffedBunnySubTotal.getText();
                String expectedSubTotalFB = fluffyBunnySubTotal.getText();
                String expectedSubTotalVB = valentineBearSubTotal.getText();

                Assert.assertEquals("$" + subTotalSF, expectedSubTotalSF);
                Assert.assertEquals("$" + subTotalFB, expectedSubTotalFB);
                Assert.assertEquals("$" + subTotalVB, expectedSubTotalVB);

                System.out.println("Test case 3.3 Successfully Verified SubTotal of each product");

                Thread.sleep(9000);

                //Finding Sub-total elements of each product
                WebElement stuffedBunnyPrice = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[2]"));
                WebElement fluffyBunnyPrice = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[2]"));
                WebElement valentineBearPrice = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[3]/td[2]"));

                //Expected price Values
                double priceSF = 10.99;
                double priceFB = 9.99;
                double priceVB = 14.99;

                //Validating the actual and expected price values
                String expectedPriceSF = stuffedBunnyPrice.getText();
                String expectedPriceFB = fluffyBunnyPrice.getText();
                String expectedPriceVB = valentineBearPrice.getText();

                Assert.assertEquals("$" + priceSF, expectedPriceSF);
                Assert.assertEquals("$" + priceFB, expectedPriceFB);
                Assert.assertEquals("$" + priceVB, expectedPriceVB);

                System.out.println("Test case 3.4 Successfully Verified Price of each product");

                WebElement total = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong"));

                Double sum = subTotalSF + subTotalFB + subTotalVB;

                Assert.assertEquals("Total: " + sum, total.getText());

                System.out.println("Test case 3.5 Successfully Verified Total");

                //Close the browser and quit the WebDriver
                closeWebDriver();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

