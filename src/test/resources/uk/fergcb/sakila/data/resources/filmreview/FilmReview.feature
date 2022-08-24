Feature: FilmReview Entity
  FilmReview Entities can be created and updated using FilmReviewDTOs

  Scenario: A filmReview is created with a full DTO
    Given the FilmReviewDTO has values for all fields
    When I create a new FilmReview
    Then the FilmReview should have all fields
    And the FilmReview's virtual fields should be null

  Scenario: A filmReview is created with an incomplete DTO
    Given the FilmReviewDTO has unset fields
    When I create a new FilmReview
    Then the FilmReview's unset fields should be null
    And the FilmReview's virtual fields should be null

  Scenario: A FilmReview has HATEOAS links
    When I create a new FilmReview
    Then the FilmReview has a "self" link