Feature: PartialActor
  PartialActor entities can be initialised and used to store Actor details

  Scenario: A PartialActor has an actorId
    Given a PartialActor
    When I set the PartialActor's actorId to 42
    Then the PartialActor's actorId should be 42

  Scenario: A PartialActor has a fullName
    Given a PartialActor
    When I set the PartialActor's fullName to "Natalie Hopkins"
    Then the PartialActor's fullName should be "Natalie Hopkins"

  Scenario: A PartialActor has HATEOAS links
    Given a PartialActor
    Then the PartialActor has a "self" link