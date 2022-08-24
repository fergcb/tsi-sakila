package uk.fergcb.sakila.data.resources.filmreview;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.*;

public class FilmReviewStepDefs {

    private final Integer expectedFilmId = 3;
    private final String expectedUserId = "ABCD-EFGH-IJKL-MNOP";
    private final String expectedAuthor = "Author";
    private final String expectedTitle = "Title";
    private final Integer expectedRating = 1999;
    private final String expectedText = "Lorem ipsum dolor sit amet...";

    FilmReview filmReview;
    FilmReviewDTO filmReviewDTO;

    @Given("the FilmReviewDTO has values for all fields")
    public void createFullDTO() {
        filmReviewDTO = new FilmReviewDTO();
        filmReviewDTO.setFilmId(expectedFilmId);
        filmReviewDTO.setUserId(expectedUserId);
        filmReviewDTO.setAuthor(expectedAuthor);
        filmReviewDTO.setTitle(expectedTitle);
        filmReviewDTO.setRating(expectedRating);
        filmReviewDTO.setText(expectedText);
    }

    @Given("the FilmReviewDTO has unset fields")
    public void createPartialDTO() {
        filmReviewDTO = new FilmReviewDTO();
    }

    @When("I create a new FilmReview")
    public void createFilmReview() {
        if (filmReviewDTO == null) {
            filmReview = new FilmReview();
        } else {
            filmReview = new FilmReview(filmReviewDTO);
        }
    }

    @Then("the FilmReview should have all fields")
    public void FilmReviewHasAllNotNullFields() {
        assertEquals(expectedAuthor, filmReview.getAuthor());
        assertEquals(expectedTitle, filmReview.getTitle());
        assertEquals(expectedRating, filmReview.getRating());
        assertEquals(expectedText, filmReview.getText());
    }

    @Then("the FilmReview's unset fields should be null")
    public void unsetFieldsAreNull() {
        assertNull(filmReview.getAuthor());
        assertNull(filmReview.getTitle());
        assertNull(filmReview.getRating());
        assertNull(filmReview.getText());
    }

    @Then("the FilmReview's virtual fields should be null")
    public void virtualFieldsAreNull() {
        assertNull(filmReview.getFilmReviewId());
    }

    @Then("the FilmReview has a {string} link")
    public void hasLink(String name) {
        assertTrue(filmReview.get_links().containsKey(name));
    }
}
