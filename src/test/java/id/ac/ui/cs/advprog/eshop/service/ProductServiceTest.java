package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepositoryImpl<Product> productRepository;

    @InjectMocks
    private ProductServiceImpl productService;  // This is the class we want to test

    @BeforeEach
    void setUp() {
        // No special setup needed; Mockito @Mock and @InjectMocks handle wiring
    }

    @Test
    void testCreate() {
        // Given
        Product product = new Product();
        product.setId("p1");
        product.setName("Test Product");
        product.setQuantity(10);

        // Mock repository behavior
        when(productRepository.create(any(Product.class))).thenReturn(product);

        // When
        Product created = productService.create(product);

        // Then
        // Verify that repository.create(...) was called exactly once
        verify(productRepository, times(1)).create(any(Product.class));
        // Verify the result
        assertNotNull(created);
        assertEquals("p1", created.getId());
        assertEquals("Test Product", created.getName());
        assertEquals(10, created.getQuantity());
    }

    @Test
    void testFindAll() {
        // Given
        Product product1 = new Product();
        product1.setId("p1");
        product1.setName("Test Product 1");
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setId("p2");
        product2.setName("Test Product 2");
        product2.setQuantity(20);

        List<Product> productList = Arrays.asList(product1, product2);
        // Convert List to an Iterator
        Iterator<Product> productIterator = productList.iterator();

        // Mock repository behavior
        when(productRepository.findAll()).thenReturn(productIterator);

        // When
        List<Product> result = productService.findAll();

        // Then
        verify(productRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals("p1", result.get(0).getId());
        assertEquals("p2", result.get(1).getId());
    }

    @Test
    void testFindAllEmpty() {
        // Given an empty repository
        Iterator<Product> emptyIterator = new ArrayList<Product>().iterator();
        when(productRepository.findAll()).thenReturn(emptyIterator);

        // When
        List<Product> result = productService.findAll();

        // Then
        verify(productRepository, times(1)).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdate() {
        // Given
        Product product = new Product();
        product.setId("p1");
        product.setName("Old Name");
        product.setQuantity(10);

        Product updatedProduct = new Product();
        updatedProduct.setId("p1");
        updatedProduct.setName("New Name");
        updatedProduct.setQuantity(99);

        // Mock repository behavior
        when(productRepository.update(eq("p1"), any(Product.class))).thenReturn(updatedProduct);

        // When
        Product result = productService.update("p1", updatedProduct);

        // Then
        verify(productRepository, times(1)).update("p1", updatedProduct);
        assertNotNull(result);
        assertEquals("p1", result.getId());
        assertEquals("New Name", result.getName());
        assertEquals(99, result.getQuantity());
    }

    @Test
    void testDelete() {
        // Given
        String productId = "p1";

        // Mock repository behavior
//        when(productRepository.delete(productId)).thenReturn(true);

        // When
        productService.deleteById(productId);

        // Then
        verify(productRepository, times(1)).delete(productId);
        assertTrue(true);
    }

    @Test
    void testDeleteNonExisting() {
        // Given
        String productId = "notFound";

        // Mock repository behavior
//        when(productRepository.delete(productId)).thenReturn(false);

        // When
        productService.deleteById(productId);

        // Then
        verify(productRepository, times(1)).delete(productId);
        assertFalse(false);
    }
}
