package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTest {

	@Test
	void contextLoads() {
		// This test ensures the Spring Boot application context can load successfully.
		// If there's any problem with the Spring configuration, this test will fail.
	}

	@Test
	void testMainMethod() {
		// Calling the main method to ensure coverage includes that line.
		// We use assertDoesNotThrow to confirm it doesn't raise any exceptions.
		assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
	}
}
