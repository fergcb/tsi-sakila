Feature: Film Entity
  Film Entities can be created and updated using FilmDTOs

  Scenario: A film is created with a full DTO
    Given the FilmDTO has values for all fields
    When I create a new Film
    Then the Film should have all fields
    And the Film's virtual fields should be null

  Scenario: A film is created with an incomplete DTO
    Given the FilmDTO has unset fields
    When I create a new Film
    Then the Film's unset fields should be null
    And the Film's virtual fields should be null

  Scenario: A Film has HATEOAS links
    When I create a new Film
    Then the Film has a "self" link
    And the Film has a "reviews" link