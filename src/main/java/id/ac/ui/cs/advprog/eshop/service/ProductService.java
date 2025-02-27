package id.ac.ui.cs.advprog.eshop.service;


import java.util.List;

public interface ProductService<T> {
    T create(T entity);
    List<T> findAll();
    T findById(String id);
    T update(String id, T entity);
    void deleteById(String id);
}