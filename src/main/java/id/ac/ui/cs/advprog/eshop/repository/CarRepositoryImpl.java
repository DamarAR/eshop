package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl extends ProductRepositoryImpl<Car> {

    @Override
    public Car update(String id, Car updatedEntity) {
        for (int i = 0; i < productData.size(); i++) {
            Car entity = (Car) productData.get(i);
            if (entity.getId().equals(id)) {
                entity.setName(updatedEntity.getName());
                entity.setQuantity(updatedEntity.getQuantity());
                entity.setColor(updatedEntity.getColor());
                return entity;
            }
        }
        return null; // Return null if no entity with the given ID is found
    }
    
}