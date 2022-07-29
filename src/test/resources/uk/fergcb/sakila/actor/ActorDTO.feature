Feature: Actor Data Transfer Object
  Actor Entities can be created and updated using ActorDTOs

  Scenario: An actor is created with a full DTO
    Given the DTO has values for all not-null fields
    When I create a new Actor
    Then the Actor should have all not-null fields

  Scenario: An actor is created with an incomplete DTO
    Given the DTO doesn't include a lastName
    When I create a new Actor
    Then the Actor should not have a lastName
