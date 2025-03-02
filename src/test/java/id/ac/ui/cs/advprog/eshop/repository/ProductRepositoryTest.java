package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
        // Re-initialize the repository before each test
        // so each test starts with a fresh "database"
        productRepository = new ProductRepositoryImpl();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    // ------------------ Tests for 'update' ------------------

    @Test
    void testUpdateExistingProduct() {
        // First create a product
        Product product = new Product();
        product.setId("12345");
        product.setName("Old Name");
        product.setQuantity(10);
        productRepository.create(product);

        // Create a product with the same ID but updated details
        Product updatedProduct = new Product();
        updatedProduct.setId("12345");
        updatedProduct.setName("New Name");
        updatedProduct.setQuantity(20);

        // Perform the update
        Product result = productRepository.update("12345",updatedProduct);

        // Verify that the update returned the product and changed the fields
        assertNotNull(result);
        assertEquals("12345", result.getId());
        assertEquals("New Name", result.getName());
        assertEquals(20, result.getQuantity());
    }

    @Test
    void testUpdateNonExistingProduct() {
        // Create a product with some ID
        Product product = new Product();
        product.setId("12345");
        product.setName("Old Name");
        product.setQuantity(10);
        productRepository.create(product);

        // Attempt to update a different ID that doesn't exist
        Product updatedProduct = new Product();
        updatedProduct.setId("67890");
        updatedProduct.setName("New Name");
        updatedProduct.setQuantity(20);

        // Should return null since the product doesn't exist
        Product result = productRepository.update("67890",updatedProduct);
        assertNull(result);
    }

    // ------------------ Tests for 'delete' ------------------

    @Test
    void testDeleteExistingProduct() {
        // Create a product
        Product product = new Product();
        product.setId("99999");
        product.setName("Name to be deleted");
        product.setQuantity(5);
        productRepository.create(product);

        // Delete it
        productRepository.delete("99999");

        // Verify it was actually removed
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonExistingProduct() {
        // Create a product
        Product product = new Product();
        product.setId("11111");
        product.setName("Non-existing check");
        product.setQuantity(5);
        productRepository.create(product);

        // Attempt to delete a product with a different ID
        productRepository.delete("22222");

        // Verify the original product is still there
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product stillPresent = productIterator.next();
        assertEquals("11111", stillPresent.getId());
    }
}
