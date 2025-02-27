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
    private ProductRepositoryImpl productRepository;

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
        product.setProductId("p1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Mock repository behavior
        when(productRepository.create(any(Product.class))).thenReturn(product);

        // When
        Product created = productService.create(product);

        // Then
        // Verify that repository.create(...) was called exactly once
        verify(productRepository, times(1)).create(any(Product.class));
        // Verify the result
        assertNotNull(created);
        assertEquals("p1", created.getProductId());
        assertEquals("Test Product", created.getProductName());
        assertEquals(10, created.getProductQuantity());
    }

    @Test
    void testFindAll() {
        // Given
        Product product1 = new Product();
        product1.setProductId("p1");
        product1.setProductName("Test Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("p2");
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(20);

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
        assertEquals("p1", result.get(0).getProductId());
        assertEquals("p2", result.get(1).getProductId());
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
        product.setProductId("p1");
        product.setProductName("Old Name");
        product.setProductQuantity(10);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("p1");
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(99);

        // Mock repository behavior
        when(productRepository.update(eq("p1"), any(Product.class))).thenReturn(updatedProduct);

        // When
        Product result = productService.update("p1", updatedProduct);

        // Then
        verify(productRepository, times(1)).update("p1", updatedProduct);
        assertNotNull(result);
        assertEquals("p1", result.getProductId());
        assertEquals("New Name", result.getProductName());
        assertEquals(99, result.getProductQuantity());
    }

    @Test
    void testDelete() {
        // Given
        String productId = "p1";

        // Mock repository behavior
        when(productRepository.delete(productId)).thenReturn(true);

        // When
        productService.deleteProductById(productId);

        // Then
        verify(productRepository, times(1)).delete(productId);
        assertTrue(true);
    }

    @Test
    void testDeleteNonExisting() {
        // Given
        String productId = "notFound";

        // Mock repository behavior
        when(productRepository.delete(productId)).thenReturn(false);

        // When
        productService.deleteProductById(productId);

        // Then
        verify(productRepository, times(1)).delete(productId);
        assertFalse(false);
    }
}
