Feature: Actor Data Entity
  Actor Entities can be created and updated using ActorDTOs

  Scenario: An actor is created with a full DTO
    Given the ActorDTO has values for all fields
    When I create a new Actor
    Then the Actor should have all fields
    And the Actor's virtual fields should be null

  Scenario: An actor is created with an incomplete DTO
    Given the ActorDTO has unset fields
    When I create a new Actor
    Then the Actor's unset fields should be null
    And the Actor's virtual fields should be null

  Scenario: An Actor has HATEOAS links
    When I create a new Actor
    Then the Actor has a "self" link