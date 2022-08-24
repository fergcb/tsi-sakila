package uk.fergcb.sakila.data.resources.film;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Set;

import static junit.framework.TestCase.*;

public class FilmStepDefs {

    private final Integer expectedFilmId = 42;
    private final String expectedTitle = "Title";
    private final String expectedDescription = "Desc";
    private final Integer expectedReleaseYear = 1999;
    private final Integer expectedLanguageId = 1;
    private final Integer expectedOriginalLanguageId = 2;
    private final Integer expectedLength = 120;
    private final String expectedRating = "U";
    private final Set<Integer> expectedActorIds = Set.of(1, 2, 3);
    private final Set<Integer> expectedCategoryIds = Set.of(1, 2, 3);

    Film film;
    FilmDTO filmDTO;

    @Given("the FilmDTO has values for all fields")
    public void createFullDTO() {
        filmDTO = new FilmDTO();
        filmDTO.setFilmId(expectedFilmId);
        filmDTO.setTitle(expectedTitle);
        filmDTO.setDescription(expectedDescription);
        filmDTO.setReleaseYear(expectedReleaseYear);
        filmDTO.setLanguageId(expectedLanguageId);
        filmDTO.setOriginalLanguageId(expectedOriginalLanguageId);
        filmDTO.setLength(expectedLength);
        filmDTO.setRating(expectedRating);
        filmDTO.setActorIds(expectedActorIds);
        filmDTO.setCategoryIds(expectedCategoryIds);
    }

    @Given("the FilmDTO has unset fields")
    public void createPartialDTO() {
        filmDTO = new FilmDTO();
    }

    @When("I create a new Film")
    public void createFilm() {
        if (filmDTO == null) {
            film = new Film();
        } else {
            film = new Film(filmDTO);
        }
    }

    @Then("the Film should have all fields")
    public void FilmHasAllNotNullFields() {
        assertEquals(expectedTitle, film.getTitle());
        assertEquals(expectedDescription, film.getDescription());
        assertEquals(expectedReleaseYear, film.getReleaseYear());
        assertEquals(expectedLength, film.getLength());
        assertEquals(expectedRating, film.getRating());
    }

    @Then("the Film's unset fields should be null")
    public void unsetFieldsAreNull() {
        assertNull(film.getTitle());
        assertNull(film.getDescription());
        assertNull(film.getReleaseYear());
        assertNull(film.getLanguage());
        assertNull(film.getOriginalLanguage());
        assertNull(film.getLength());
        assertNull(film.getRating());
    }

    @Then("the Film's virtual fields should be null")
    public void virtualFieldsAreNull() {
        assertNull(film.getFilmId());
        assertNull(film.getLanguage());
        assertNull(film.getOriginalLanguage());
        assertNull(film.getCategories());
        assertNull(film.getCast());
    }

    @Then("the Film has a {string} link")
    public void hasLink(String name) {
        assertTrue(film.get_links().containsKey(name));
    }
}
