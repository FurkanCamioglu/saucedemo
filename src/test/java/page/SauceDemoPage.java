package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import utilities.ConfigReader;
import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ReasuableMethods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SauceDemoPage {

    public SauceDemoPage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement loginUsername;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement loginPassword;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement inventoryProductsText;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement inventoryAddToCartBackpack;

    @FindBy(xpath = "//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")
    private WebElement inventoryAddToCartRedTShirt;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement inventoryAddToCartBoltTShirt;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-onesie']")
    private WebElement inventoryAddToCartOnesie;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement inventoryAddToCartBikeLight;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement inventoryAddToCartFleeceJacket;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement inventoryAddToCartItemNumber;

    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    private WebElement cartItemBackpack;

    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Bolt T-Shirt']")
    private WebElement cartItemBoltTShirt;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement inventoryAddToCartButton;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement checkoutStep1FirstName;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement checkoutStep1LastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement checkoutStep1Zip;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement checkoutStep1tContinueButton;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private WebElement priceList;

    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    private WebElement checkoutStep2sumPrice;

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement checkoutStep2FinishButton;

    @FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']")
    private WebElement checkoutCompleteVerifyOrder;


    public void goToUrl(String page) {

        Driver.getDriver().get(ConfigReader.getProperty(page));

    }

    public void enterDatas(String username, String password) {

        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);

    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void verifyLogin() {
        Assert.assertTrue(inventoryProductsText.isDisplayed());
    }

    public void doesntVerifyLogin() {
        Assert.assertTrue(loginButton.isDisplayed());
    }

    public void addingStaffToCart() throws InterruptedException {

        inventoryAddToCartBikeLight.click();
        inventoryAddToCartBackpack.click();
        inventoryAddToCartBoltTShirt.click();
        inventoryAddToCartFleeceJacket.click();
        ReasuableMethods.scroolToElement(inventoryAddToCartOnesie);
        Thread.sleep(1000);
        inventoryAddToCartOnesie.click();
        inventoryAddToCartRedTShirt.click();

    }

    public void verifiesAddingItems() {
        Assert.assertTrue(inventoryAddToCartItemNumber.isDisplayed());
        Assert.assertEquals("6", inventoryAddToCartItemNumber.getText());

    }

    public void addingItemsOnCartMenu() {

        inventoryAddToCartBackpack.click();
        inventoryAddToCartBoltTShirt.click();
        inventoryAddToCartRedTShirt.click();

    }

    public void verifiesToNavigateCartMenu() {
        Driver.getDriver().navigate().to("https://www.saucedemo.com/cart.html");

        Assert.assertTrue(checkoutButton.isDisplayed());

    }

    public void checkingAddingItemsOnCartMenu() {


        Assert.assertTrue(cartItemBackpack.isDisplayed());
        Assert.assertTrue(cartItemBoltTShirt.isDisplayed());

    }

    public void enterPositiveDatasToCheckoutMenu(){

        checkoutButton.click();
        checkoutStep1FirstName.sendKeys("ff");
        checkoutStep1LastName.sendKeys("ff");
        checkoutStep1Zip.sendKeys("L3C 5OT");
        checkoutStep1tContinueButton.click();

    }

    public void checkingItemsAndPrice(){

        List<WebElement> priceList = Driver.getDriver().findElements(By.xpath("//div[@class='inventory_item_price'][normalize-space()]"));

        double toplam=0;

        for (int i = 0; i <priceList.size(); i++) {

            double text = Double.parseDouble(priceList.get(i).getText().replaceAll("[$]",""));

            toplam = toplam + text;

        }

        //System.out.println(toplam);

        double taxRate = toplam *0.08;
        double result = toplam+taxRate;
        //System.out.println(result);

        BigDecimal result2 = new BigDecimal(result).setScale(2,RoundingMode.CEILING);
       // System.out.println(result2);

        double result3 = result2.doubleValue();
        double totalPrice = Double.parseDouble(checkoutStep2sumPrice.getText().replaceAll("[^\\d.]", ""));
        double delta = 0.0001;

        System.out.println(result3);
        System.out.println(totalPrice);

    Assert.assertEquals(result3,totalPrice,delta);

//        Price Total
//        Item total: $45.98
//        Tax: $3.68
//        Total: $49.66

    }

    public void clickFinishAndVerifyOrdering(){

       checkoutStep2FinishButton.click();

       Assert.assertTrue(checkoutCompleteVerifyOrder.isDisplayed());


    }

    public void enterNegativeDatasToCheckoutMenu(String firstname, String lastname, String zipCode){

        checkoutButton.click();
        checkoutStep1FirstName.sendKeys(firstname);
        checkoutStep1LastName.sendKeys(lastname);
        checkoutStep1Zip.sendKeys(zipCode);
        checkoutStep1tContinueButton.click();

    }

    public void verifyAdminShouldNotPassNextStep(){

        Assert.assertTrue(checkoutStep1tContinueButton.isDisplayed());

    }

}
