package uk.co.edgewords;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import org.hamcrest.MatcherAssert;

import static org.hamcrest.CoreMatchers.*;
import static uk.co.edgewords.hooks.Hooks.driver;

public class ShoppingSteps {

    @Given("I am on {string}")
    public void iAmOn(String theUrl) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(theUrl);
        driver.findElement(By.id("woocommerce-product-search-field-0")).click();
    }

    @When("I search for Edgewords")
    public void i_search_for_edgewords() {
        iSearchFor("Edgewords");
    }

    @Then("Edgewords is the top result")
    public void edgewords_is_the_top_result() {

        String firstResult = driver.findElement(By.cssSelector("#rso > div:first-child")).getText();
        MatcherAssert.assertThat(firstResult, containsString("Edgewords"));
    }


    @When("I search for {string}")
    //@When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchTerm) {
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys(searchTerm + Keys.ENTER);
    }


    @Then("{string} is the top result")
    public void edgewordsIsTheTopResult(String result) {
        String firstResult = driver.findElement(By.cssSelector("#rso > div:first-child")).getText();
        MatcherAssert.assertThat(firstResult, containsString(result));
    }

    @Then("I arrive at the {string} page")
    public void checkUrl(String theName) {
        String theTitle = driver.findElement(By.cssSelector(".entry-title.product_title")).getText();
        MatcherAssert.assertThat(theTitle, containsString(theName));
    }

    @When("I add to basket")
    public void basketAdd() {
        driver.findElement(By.cssSelector("button[name='add-to-cart']")).click();
    }

    @Then("I can see {string} in the basket")
    public void iCanSeeInTheBasket(String product) {
        driver.findElement(By.cssSelector("a[title='View your shopping cart']")).click();
        String basketItem = driver.findElement(By.cssSelector(".product-name > a")).getText();
        MatcherAssert.assertThat(basketItem, containsString(product));
    }

    @When("I apply discount code {string}")
    //@When("^I search for \"([^\"]*)\"$")
    public void discount(String theCode) {
        driver.findElement(By.cssSelector("input#coupon_code")).sendKeys(theCode + Keys.ENTER);
    }

    @Then("The price will be {string}")
    public void checkPrice(String wantedPrice) {
        String shownPrice = driver.findElement(By.cssSelector("strong > .amount.woocommerce-Price-amount > bdi")).getText();
        MatcherAssert.assertThat(shownPrice, containsString(wantedPrice));
    }
    @When("I proceed to checkout")
    public void finishAndPay() {
        driver.findElement(By.cssSelector(".alt.button.checkout-button.wc-forward")).click();
    }

    @When("I enter my account")
    public void iEnterMyAccount() {
        driver.findElement(By.cssSelector(".menu-item.menu-item-46.menu-item-object-page.menu-item-type-post_type > a")).click();
    }

    @And("Enter username")
    public void enterUsername() {
        driver.findElement(By.cssSelector("input#username")).sendKeys("scott.hamilton@2itesting.com");
    }

    @And("Enter password")
    public void enterPassword() {
        driver.findElement(By.cssSelector("input#password")).sendKeys("atBacon256" + Keys.ENTER);
    }

    @Then("I will see {string} greeting")
    public void iWillSeeGreeting(String accName) {
        String shownName = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content > p:nth-of-type(1) > strong:nth-of-type(1)")).getText();
        MatcherAssert.assertThat(shownName, containsString(accName));
    }

    @And("Select Cash")
    public void selectCash() {
        driver.findElement(By.cssSelector(".payment_method_cod.wc_payment_method > label")).click();
    }


    @And("Place Order")
    public void placeOrder() {
        driver.findElement(By.cssSelector("button#place_order")).click();
    }


    @Then("Order number will be known between pages")
    public void orderNumberWillBeKnown() {
        String orderInit = driver.findElement(By.cssSelector(".order > strong")).getText();
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/orders/");
        String orderAcc = driver.findElement(By.cssSelector("tr:nth-of-type(1) > .woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a")).getText();
        MatcherAssert.assertThat(orderInit, containsString(orderAcc));
    }
}
