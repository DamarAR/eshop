https://proper-zea-damarar-83e9d155.koyeb.app/


## Week 1
### Reflection 1

I applied several clean code principles discussed in class, including the use of meaningful and descriptive naming conventions. For example, I ensured that variables like productID clearly indicate their purpose as product identifiers. Additionally, I adhered to the Single Responsibility Principle (SRP) by properly structuring the controller, service, and repository layers to maintain separation of concerns. To reduce code duplication, I reused existing logic within service and repository methods. I also implemented secure coding practices, such as incorporating confirmation prompts for delete actions.

Moving forward, I see opportunities for further improvement, such as adding input validation using annotations like @NotNull and @Size, as well as improving error handling by introducing custom error pages. Moreover, I plan to enhance security by implementing authorization checks to prevent unauthorized access to editing and deleting functionalities.

### Reflection 2

After writing unit tests, I realized their crucial role in software development. The number of unit tests required depends on the complexity of the function being tested. Within a class, there should be sufficient test cases to validate different scenarios effectively. However, achieving a perfect code coverage does not necessarily mean the code is free of bugs and errors—we must still account for all possible input scenarios.

If we were tasked with creating another functional test suite to verify the number of items in the product list, I might end up duplicating the setup code from CreateProductFunctionalTest.java. While this approach would work, it could lead to code redundancy, ultimately affecting code cleanliness and quality. A notable clean code issue in this scenario is the repetition of instance variables such as serverPort, testBaseUrl, and baseUrl, which would require updates in multiple files whenever changes occur.

To address this redundancy, a better approach would be to introduce a new class that centralizes the common setup logic. This class would manage browser setup, server port initialization, and URL configuration, allowing all functional test classes to inherit these configurations and maintain cleaner, more maintainable code.


## Week 2
### Reflection

During the exercise, I resolved a permission problem in ci.yml and dockerfile by adding chmod +x gradlew before executing the build command. I also resolved a problem with a test case by introducing an absent assertion to ensure that there were validations, thereby enhancing the credibility of tests. I also introduced SonarCloud into the project for code quality checking and potential problems detection earlier in the development process. My current CI/CD setup embraces Continuous Integration by automatically testing and checking the quality of code on every push or pull request and embracing Continuous Deployment by promoting the latest updates to Koyeb automatically as soon as updates are merged to the main branch. This automation not only reduces the risk of human error but also guarantees high code quality and enables faster, more stable deployments.


## Week 3
### Reflection


1) **Explain what principles you apply to your project!**

#### Single Responsibility Principle (SRP)
Each class has a single responsibility. For example:
- `ProductServiceImpl` handles business logic for `Product`.
- `CarServiceImpl` handles business logic for `Car`.

#### Open/Closed Principle (OCP)
Classes are open for extension but closed for modification. For instance:
- `ProductServiceImpl` can be extended to add new functionalities without modifying existing code.

#### Liskov Substitution Principle (LSP)
Subtypes can replace their base types without altering the correctness of the program.
- `Car` extends `Product`, ensuring that `Car` can be used wherever `Product` is expected.

#### Interface Segregation Principle (ISP)
Interfaces are specific to what clients need.
- `ProductService` interface is specific to product-related operations, ensuring that `ProductServiceImpl` only implements relevant methods.

#### Dependency Inversion Principle (DIP)
High-level modules depend on abstractions rather than low-level modules.
- `ProductServiceImpl` depends on `ProductRepositoryImpl<Product>` abstraction, promoting decoupling.

---

2) **Advantages of Applying SOLID Principles**

#### Maintainability
Code is easier to maintain and update.
- Adding a new feature to `ProductServiceImpl` does not require changes to other parts of the codebase.

#### Reusability
Components can be reused across different parts of the application.
- `ProductRepositoryImpl` can be reused for different product-related operations.

#### Testability
Code is easier to test.
- `ProductControllerTest` can mock `ProductService` to test the controller in isolation.

#### Scalability
The application can grow without becoming unmanageable.
- New features can be added by extending existing classes and interfaces.

#### Flexibility
The system can adapt to changes more easily.
- Switching from one repository implementation to another does not require changes to the service layer.

---

3) **Explain the disadvantages of not applying SOLID principles to your project with examples.**

#### Tight Coupling
Without DIP, high-level modules depend on low-level modules, making the system rigid and difficult to change.
- If `ProductServiceImpl` directly depends on a specific repository implementation, changing the repository would require changes to the service.

#### Code Duplication
Without SRP, multiple classes might handle the same responsibility, leading to duplicated code.
- If both `ProductServiceImpl` and `CarServiceImpl` handle common logic, changes would need to be made in multiple places.

#### Difficult Testing
Without ISP, classes might implement unnecessary methods, making them harder to test.
- If `ProductService` included methods irrelevant to `ProductServiceImpl`, testing would become more complex.

#### Inflexibility
Without OCP, adding new features requires modifying existing code, increasing the risk of introducing bugs.
- Adding a new product type would require changes to the existing `ProductServiceImpl`.

#### Incorrect Substitution
Without LSP, subclasses might not behave correctly when used in place of their base classes.
- If `Car` did not correctly extend `Product`, it could cause runtime errors when used as a `Product`.


## Week 4
### Reflection
Code Quality Improvements
- I made a minor enhancement to the contextLoads test in the application by adding descriptive comments. This update clarifies that the test's purpose is to verify the successful loading of the Spring Boot application context, even though no additional logic was required. While a small change, it improves code readability and ensures that future developers can easily understand the test’s intent.

CI/CD Workflow Reflection
- The current CI/CD implementation adheres to the fundamental principles of Continuous Integration and Continuous Deployment. The workflows automatically execute test suites and conduct code quality analysis on every commit, providing immediate feedback and ensuring seamless integration of new changes without compromising system stability. Additionally, automated deployment to a PaaS ensures that validated changes are swiftly and reliably released to production. This setup effectively reduces the risk of defects and aligns well with modern agile methodologies, promoting fast and dependable software delivery.

## Week 4.2
### Reflection
Explain what you think about your partner’s code? Are there any aspects that are still lacking from your partner’s code?
- So far my friends code are pretty much good, although there are some implementation that could be enhanced so that the code can run better.

What did you do to contribute to your partner’s code?
- I'm changing the contains method in his OrderStatus code by using Set for faster lookups.

What code smells did you find on your partner’s code?
- One potential code smell in the OrderStatus enum is the contains method, which iterates over the enum values to check if a given string matches any of the enum names. This can be optimized using a Set for faster lookups.

What refactoring steps did you suggest and execute to fix those smells?
- Identify the Code Smell: The contains method iterates over the enum values to check if a given string matches any of the enum names. This can be optimized for faster lookups.
- Introduce a Set for Faster Lookups: Use a Set to store the enum names, which allows for O(1) average time complexity for lookups.
- Initialize the Set in a Static Block: Populate the Set with the enum names in a static block to ensure it is done only once when the class is loaded.
- Refactor the contains Method: Modify the contains method to use the Set for checking if a given string is a valid enum name.
