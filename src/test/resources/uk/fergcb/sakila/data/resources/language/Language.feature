Feature: Language Data Entity
  Language Entities can be created and updated using LanguageDTOs

  Scenario: An language is created with a full DTO
    Given the LanguageDTO has values for all fields
    When I create a new Language
    Then the Language should have all fields
    And the Language's virtual fields should be null

  Scenario: An language is created with an incomplete DTO
    Given the LanguageDTO has unset fields
    When I create a new Language
    Then the Language's unset fields should be null
    And the Language's virtual fields should be null