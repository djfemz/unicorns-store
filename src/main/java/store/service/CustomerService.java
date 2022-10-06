package store.service;

import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest);
    String orderProduct(ProductPurchaseRequest productPurchaseRequest);

}
