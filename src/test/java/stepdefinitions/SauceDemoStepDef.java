package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import page.SauceDemoPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;


public class SauceDemoStepDef {

    SauceDemoPage sauceDemoPage = new SauceDemoPage();

    @Given("Admin goes to login page {string}")
    public void admin_goes_to_login_page(String page) {

        sauceDemoPage.goToUrl(page);

    }
    @And("Admin fills with positive datas and click login button")
    public void admin_fills_with_positive_datas_and_click_login_button(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String,String>> login = dataTable.asMaps(String.class,String.class);

        sauceDemoPage.enterDatas(login.get(0).get("username"),login.get(0).get("password"));
        sauceDemoPage.clickLoginButton();
    }
    @Then("Admin verifys the page navigates to inventory")
    public void admin_verifys_the_page_navigates_to_inventory() {
        sauceDemoPage.verifyLogin();

    }
    @Then("Admin closes the browser")
    public void admin_closes_the_browser() {
        Driver.quitDriver();

    }

    @And("Admin fills with negative datas {string}, {string} and click login button")
    public void adminFillsWithNegativeDatasAndClickLoginButton(String username, String password) {
        sauceDemoPage.enterDatas(username,password);
        sauceDemoPage.clickLoginButton();

    }

    @Then("Admin verifys the page doesnt navigates to inventory")
    public void adminVerifysThePageDoesntNavigatesToInventory() {
        sauceDemoPage.doesntVerifyLogin();
    }

    @Given("Admin adds staffs to the cart")
    public void adminAddsStaffsToTheCart() throws InterruptedException {
        sauceDemoPage.addingStaffToCart();
    }

    @Then("Admin verifies staffs added to the cart")
    public void adminVerifiesStaffsAddedToTheCart() {
        sauceDemoPage.verifiesAddingItems();
    }

    @Given("Admin adds items to the cart")
    public void adminAddsItemsToTheCart() {
        sauceDemoPage.addingItemsOnCartMenu();
    }

    @Then("Admin verifies the page navigates to cart")
    public void adminVerifiesThePageNavigatesToCart() {
        sauceDemoPage.verifiesToNavigateCartMenu();
    }

    @Then("Admin verifies Admin can see items which he added to cart")
    public void adminVerifiesAdminCanSeeItemsWhichHeAddedToCart() {
        sauceDemoPage.checkingAddingItemsOnCartMenu();
    }

    @Given("Admin navigates checkout page and enters positive datas")
    public void adminNavigatesCheckoutPageAndEntersPositiveDatas() {

        sauceDemoPage.enterPositiveDatasToCheckoutMenu();

    }

    @Then("Admin verifies the items and total prices")
    public void adminVerifiesTheItemsAndTotalPrices() {

        sauceDemoPage.checkingItemsAndPrice();

    }

    @Then("Admin verifies order is completed")
    public void adminVerifiesOrderIsCompleted() {

        sauceDemoPage.clickFinishAndVerifyOrdering();

    }

    @And("Admin navigates checkout page and enters negative datas {string}, {string}, {string}")
    public void adminNavigatesCheckoutPageAndEntersNegativeDatas(String firstname, String lastname, String zipCode) {

        sauceDemoPage.enterPositiveDatasToCheckoutMenu();
    }

    @Then("Admin shouldnt pass next step")
    public void adminShouldntPassNextStep() {

        sauceDemoPage.verifyAdminShouldNotPassNextStep();

    }
}
