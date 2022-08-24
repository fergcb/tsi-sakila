package uk.fergcb.sakila.data.resources.filmactor;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FilmActorStepDefs {

    final Integer expectedFilmId = 42;
    final Integer expectedActorId = 123;

    FilmActor filmActor;
    FilmActorKey filmActorKey;

    @When("I create a FilmActor")
    public void createFilmActor() {
        filmActor = new FilmActor(expectedFilmId, expectedActorId);
    }

    @Then("the FilmActor has a FilmActorKey")
    public void getFilmActorKey() {
        filmActorKey = filmActor.getFilmActorKey();
        assertNotNull(filmActorKey);
    }

    @Then("the FilmActorKey has the expected filmId")
    public void getFilmId() {
        assertEquals(expectedFilmId, filmActorKey.getFilmId());
    }

    @Then("the FilmActorKey has the expected actorId")
    public void getActorId() {
        assertEquals(expectedActorId, filmActorKey.getActorId());
    }

}
