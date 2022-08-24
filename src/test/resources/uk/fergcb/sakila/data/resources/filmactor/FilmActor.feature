Feature: FilmActor

  Scenario: A FilmActor can be created
    When I create a FilmActor
    Then the FilmActor has a FilmActorKey
    And the FilmActorKey has the expected filmId
    And the FilmActorKey has the expected actorId