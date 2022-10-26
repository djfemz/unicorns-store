package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.exceptions.BuyerRegistrationException;
import store.service.CustomerService;
import store.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody CustomerRegistrationRequest
                                                             customerRegistrationRequest){
        try {
            CustomerRegistrationResponse response =
                    customerService.register(customerRegistrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (BuyerRegistrationException exception){
             CustomerRegistrationResponse response = new CustomerRegistrationResponse();
             response.setMessage(exception.getMessage());
             response.setStatusCode(401);
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
