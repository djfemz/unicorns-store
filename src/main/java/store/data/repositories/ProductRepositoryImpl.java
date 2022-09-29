package store.data.repositories;

import store.data.models.Product;
import store.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository{

    private final List<Product> productDb = new ArrayList<>();

    @Override
    public Product save(Product product) {
        product.setId(generateId());
        productDb.add(product);
        return product;
    }

    @Override
    public Product findById(int id) {
        return productDb.stream()
                .filter(product -> product.getId()==id)
                .collect(Collectors.toSet())
                .stream().findFirst().orElseThrow(()->new
                        ProductNotFoundException(String.format("product with id %d not found", id)));
    }

    @Override
    public List<Product> findAll() {
        return productDb;
    }

    @Override
    public void delete(Product product) {
        productDb.remove(product);
    }
    private int generateId(){
        return productDb.size()+1;
    }


}
