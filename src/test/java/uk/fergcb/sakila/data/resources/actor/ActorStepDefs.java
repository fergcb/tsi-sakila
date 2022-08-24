package uk.fergcb.sakila.data.resources.actor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.*;

public class ActorStepDefs {

    private final String expectedFirstName = "First";
    private final String expectedLastName = "Last";

    Actor actor;
    ActorDTO actorDTO;

    @Given("the ActorDTO has values for all fields")
    public void createFullDTO() {
        actorDTO = new ActorDTO();
        actorDTO.setFirstName(expectedFirstName);
        actorDTO.setLastName(expectedLastName);
    }

    @Given("the ActorDTO has unset fields")
    public void createPartialDTO() {
        actorDTO = new ActorDTO();
    }

    @When("I create a new Actor")
    public void createActor() {
        if (actorDTO == null) {
            actor = new Actor();
        } else {
            actor = new Actor(actorDTO);
        }
    }

    @Then("the Actor should have all fields")
    public void actorHasAllNotNullFields() {
        assertEquals(expectedFirstName, actor.getFirstName());
        assertEquals(expectedLastName, actor.getLastName());
    }

    @Then("the Actor's unset fields should be null")
    public void unsetFieldsAreNull() {
        assertNull("lastName should be null", actor.getLastName());
    }

    @Then("the Actor's virtual fields should be null")
    public void virtualFieldsAreNull() {
        assertNull(actor.getActorId());
        assertNull(actor.getFilms());
    }

    @Then("the Actor has a {string} link")
    public void hasLink(String name) {
        assertTrue(actor.get_links().containsKey(name));
    }
}
