package store.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Category;
import store.data.models.Product;
import store.data.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(addProductRequest.getPrice()));
        product.setCategory(Category.valueOf(addProductRequest
                .getCategory().toUpperCase()));
        product.setName(addProductRequest.getName());
        Product savedProduct = productRepository.save(product);

        AddProductResponse response = new AddProductResponse();
        response.setProductId(savedProduct.getId());
        response.setMessage("product created successfully");
        response.setStatusCode(201);
        return response;
    }

    @Override
    public Product getProductById(int id) {
        Product foundProduct = productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Oops!!"));
        return foundProduct;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
