package uk.fergcb.sakila.data.resources.language;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class LanguageStepDefs {

    private final String expectedName = "Esperanto";

    Language language;
    LanguageDTO languageDTO;

    @Given("the LanguageDTO has values for all fields")
    public void createFullDTO() {
        languageDTO = new LanguageDTO();
        languageDTO.setName(expectedName);
    }

    @Given("the LanguageDTO has unset fields")
    public void createPartialDTO() {
        languageDTO = new LanguageDTO();
    }

    @When("I create a new Language")
    public void createLanguage() {
        if (languageDTO == null) {
            language = new Language();
        } else {
            language = new Language(languageDTO);
        }
    }

    @Then("the Language should have all fields")
    public void languageHasAllNotNullFields() {
        assertEquals(expectedName, language.getName());
    }

    @Then("the Language's unset fields should be null")
    public void unsetFieldsAreNull() {
        assertNull(language.getName());
    }

    @Then("the Language's virtual fields should be null")
    public void virtualFieldsAreNull() {
        assertNull(language.getLanguageId());
    }
    
}
