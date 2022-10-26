package store.service;

import org.springframework.stereotype.Component;
import store.data.dto.*;
import store.data.models.Customer;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);

    List<Customer> getAllCustomers();

    String orderProduct(ProductPurchaseRequest productPurchaseRequest);

}
