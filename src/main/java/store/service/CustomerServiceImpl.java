package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.dto.*;
import store.data.models.Customer;
import store.data.models.Product;
import store.data.repositories.CustomerRepository;
import store.exceptions.BuyerRegistrationException;
import store.exceptions.StoreException;
import store.utils.validators.UserDetailsValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private ProductService productService;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {
        //validate registration email
        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail()))
            throw new BuyerRegistrationException(String
                    .format("email %s is invalid", registrationRequest.getEmail()));
        //validate buyer password
        if (!UserDetailsValidator.isValidPassword(registrationRequest.getPassword()))
            throw new BuyerRegistrationException(String
                    .format("password %s is invalid", registrationRequest.getPassword()));

        //validate buyer registration phone number
        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNumber()))
            throw new BuyerRegistrationException(String
                    .format("phone number %s not valid", registrationRequest.getPhoneNumber()));
        //create buyer
        Customer customer = buildBuyer(registrationRequest);
        //save buyer
        Customer savedCustomer = customerRepository.save(customer);
        //create registration response object
        CustomerRegistrationResponse response =
                buildBuyerRegistrationResponse(savedCustomer);
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        //check the db for a customer with email that is the same as email in login request
        Customer foundCustomer =
                customerRepository.findByEmail(loginRequest.getEmail())
                        .orElseThrow(()->new RuntimeException("Oops!!"));

        LoginResponse loginResponse = new LoginResponse();
        //compare password of found customer to password in login request
        if (foundCustomer.getPassword().equals(loginRequest.getPassword())){
            loginResponse.setMessage("successful login");
            return loginResponse;
        }
        loginResponse.setMessage("authentication failed");
        return loginResponse;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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
        Customer customer =
                customerRepository.findById(productPurchaseRequest
                        .getCustomerId())
                        .orElseThrow(()->new RuntimeException("customer not found"));
        //search for product
        Product product =
                productService.getProductById(productPurchaseRequest.getProductId());
        if (product==null) throw new StoreException("product not found");
        //validate quantity
        if (product.getQuantity()>= productPurchaseRequest.getQuantity()){
            customer.getOrders().add(product);
            product.setQuantity(product.getQuantity()-productPurchaseRequest.getQuantity());
            productService.save(product);
            customerRepository.save(customer);
            return "order successful";
        }else{
            throw new StoreException("order quantity larger than available quantity");
        }
    }
}
