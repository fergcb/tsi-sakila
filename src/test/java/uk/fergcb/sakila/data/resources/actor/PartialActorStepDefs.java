package uk.fergcb.sakila.data.resources.actor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PartialActorStepDefs {

    PartialActor partialActor;

    @Given("a PartialActor")
    public void aPartialActor() {
        partialActor = new PartialActor();
    }

    @When("I set the PartialActor's actorId to {int}")
    public void setActorId(Integer actorId) {
        partialActor.setActorId(actorId);
    }

    @When("I set the PartialActor's fullName to {string}")
    public void setFullName(String fullName) {
        partialActor.setFullName(fullName);
    }

    @Then("the PartialActor's actorId should be {int}")
    public void getActorId(Integer actorId) {
        assertEquals(actorId, partialActor.getActorId());
    }

    @Then("the PartialActor's fullName should be {string}")
    public void getFullName(String fullName) {
        assertEquals(fullName, partialActor.getFullName());
    }

    @Then("the PartialActor has a {string} link")
    public void hasLink(String name) {
        assertTrue(partialActor.get_links().containsKey(name));
    }

}
