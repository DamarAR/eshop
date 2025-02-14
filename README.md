Reflection 1

I applied several clean code principles discussed in class, including the use of meaningful and descriptive naming conventions. For example, I ensured that variables like productID clearly indicate their purpose as product identifiers. Additionally, I adhered to the Single Responsibility Principle (SRP) by properly structuring the controller, service, and repository layers to maintain separation of concerns. To reduce code duplication, I reused existing logic within service and repository methods. I also implemented secure coding practices, such as incorporating confirmation prompts for delete actions.

Moving forward, I see opportunities for further improvement, such as adding input validation using annotations like @NotNull and @Size, as well as improving error handling by introducing custom error pages. Moreover, I plan to enhance security by implementing authorization checks to prevent unauthorized access to editing and deleting functionalities.

Reflection 2

After writing unit tests, I realized their crucial role in software development. The number of unit tests required depends on the complexity of the function being tested. Within a class, there should be sufficient test cases to validate different scenarios effectively. However, achieving a perfect code coverage does not necessarily mean the code is free of bugs and errors—we must still account for all possible input scenarios.

If we were tasked with creating another functional test suite to verify the number of items in the product list, I might end up duplicating the setup code from CreateProductFunctionalTest.java. While this approach would work, it could lead to code redundancy, ultimately affecting code cleanliness and quality. A notable clean code issue in this scenario is the repetition of instance variables such as serverPort, testBaseUrl, and baseUrl, which would require updates in multiple files whenever changes occur.

To address this redundancy, a better approach would be to introduce a new class that centralizes the common setup logic. This class would manage browser setup, server port initialization, and URL configuration, allowing all functional test classes to inherit these configurations and maintain cleaner, more maintainable code.