Feature: FilmCategory

  Scenario: A FilmCategory can be created
    When I create a FilmCategory
    Then the FilmCategory has a FilmCategoryKey
    And the FilmCategoryKey has the expected filmId
    And the FilmCategoryKey has the expected categoryId