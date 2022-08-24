package uk.fergcb.sakila.data.resources.category;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertEquals;

public class CategoryStepDefs {

    Category category;

    @Given("a Category")
    public void aCategory() {
        category = new Category();
    }

    @When("I set the Category's categoryId to {int}")
    public void setCategoryId(Integer categoryId) {
        category.setCategoryId(categoryId);
    }

    @When("I set the Category's name to {string}")
    public void setName(String name) {
        category.setName(name);
    }

    @Then("the Category's categoryId should be {int}")
    public void getCategoryId(Integer categoryId) {
        assertEquals(categoryId, category.getCategoryId());
    }

    @Then("the Category's name should be {string}")
    public void getName(String name) {
        assertEquals(name, category.getName());
    }

}
