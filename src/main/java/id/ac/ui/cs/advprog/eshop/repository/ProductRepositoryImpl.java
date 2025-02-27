package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl <T extends Product> implements ProductRepository<T> {
    protected List<T> productData = new ArrayList<>();

    @Override
    public T create(T entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        productData.add(entity);
        return entity;
    }

    @Override
    public Iterator<T> findAll() {
        return productData.iterator();
    }

    @Override
    public T findById(String id) {
        for (T entity : productData) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public T update(String id, T updatedEntity) {
        for (int i = 0; i < productData.size(); i++) {
            T entity = productData.get(i);
            if (entity.getId().equals(id)) {
                entity.setName(updatedEntity.getName());
                entity.setQuantity(updatedEntity.getQuantity());
                return entity;
            }
        }
        return null; // Return null if no entity with the given ID is found
    }

    @Override
    public void delete(String id) {
        productData.removeIf(entity -> entity.getId().equals(id));
    }
}