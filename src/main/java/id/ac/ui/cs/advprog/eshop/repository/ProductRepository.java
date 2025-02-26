package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll () {
        return productData.iterator();

    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product update(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                // Update the existing car with the new information
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        return null; // Handle the case where the car is not found
    }

    public boolean delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() != null && product.getProductId().equals(productId)) {
                iterator.remove();
                return true;  // Successfully deleted
            }
        }
        return false;  // Product not found
    }
}