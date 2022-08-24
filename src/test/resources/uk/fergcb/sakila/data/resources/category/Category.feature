Feature: Category
  Category entities can be initialised and used to store category details

  Scenario: A Category has a categoryId
    Given a Category
    When I set the Category's categoryId to 42
    Then the Category's categoryId should be 42

  Scenario: A Category has a name
    Given a Category
    When I set the Category's name to "Action"
    Then the Category's name should be "Action"