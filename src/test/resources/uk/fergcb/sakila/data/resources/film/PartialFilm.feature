Feature: PartialFilm
  PartialFilm entities can be initialised and used to store Film details

  Scenario: A PartialFilm has a filmId
    Given a PartialFilm
    When I set the PartialFilm's filmId to 42
    Then the PartialFilm's filmId should be 42

  Scenario: A PartialFilm has a title
    Given a PartialFilm
    When I set the PartialFilm's title to "Ace Goldfinger"
    Then the PartialFilm's title should be "Ace Goldfinger"

  Scenario: A PartialFilm has a description
    Given a PartialFilm
    When I set the PartialFilm's description to "Lorem ipsum dolor sit amet..."
    Then the PartialFilm's description should be "Lorem ipsum dolor sit amet..."

  Scenario: A PartialFilm has a releaseYear
    Given a PartialFilm
    When I set the PartialFilm's releaseYear to 1999
    Then the PartialFilm's releaseYear should be 1999

  Scenario: A PartialFilm has HATEOAS links
    Given a PartialFilm
    Then the PartialFilm has a "self" link