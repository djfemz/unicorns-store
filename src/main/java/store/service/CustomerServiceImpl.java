package store.service;

import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.data.models.Customer;
import store.data.repositories.CustomerRepository;
import store.data.repositories.CustomerRepositoryImpl;
import store.exceptions.BuyerRegistrationException;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {
        //validate registration email
        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail()))
            throw new BuyerRegistrationException(String
                    .format("email %s is invalid", registrationRequest.getEmail()));
        //validate buyer registration phone number
        if (!UserDetailsValidator.isValidPassword(registrationRequest.getPassword()))
            throw new BuyerRegistrationException(String
                    .format("phone number %s is invalid", registrationRequest.getPhoneNumber()));

        //validate buyer password
        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNumber()))
            throw new BuyerRegistrationException(String
                    .format("password %s is weak", registrationRequest.getPassword()));
        //create buyer
        Customer customer = buildBuyer(registrationRequest);
        //save buyer
        Customer savedCustomer = customerRepository.save(customer);
        //create registration response object
        CustomerRegistrationResponse response =
                buildBuyerRegistrationResponse(savedCustomer);
        return response;
    }



    private CustomerRegistrationResponse buildBuyerRegistrationResponse(Customer savedCustomer) {
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("user registration successful");
        response.setStatusCode(201);
        response.setUserId(savedCustomer.getId());
        return response;
    }

    private Customer buildBuyer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = new Customer();
        customer.setEmail(registrationRequest.getEmail());
        customer.setPassword(registrationRequest.getPassword());
        Set<String> buyersAddressList = customer.getDeliveryAddresses();
        buyersAddressList.add(registrationRequest.getAddress());
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        return customer;
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {
        //search for product
        //validate quantity
        //

        return null;
    }
}
