package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.AddProductRequest;
import store.data.dto.BuyerRegistrationRequest;
import store.data.dto.BuyerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.exceptions.BuyerRegistrationException;

import static org.junit.jupiter.api.Assertions.*;

class BuyerServiceImplTest {

    private final BuyerService buyerService=
            new BuyerServiceImpl();
    private final ProductService productService=
            new ProductServiceImpl();

    private BuyerRegistrationRequest firstBuyerRegisterRequest;
    private BuyerRegistrationRequest secondBuyerRegisterRequest;
    private ProductPurchaseRequest productPurchaseRequest;
    private AddProductRequest addProductRequest;


    @BeforeEach
    void setUp() {
        firstBuyerRegisterRequest=new BuyerRegistrationRequest();
        firstBuyerRegisterRequest.setEmail("ademusa222@gmail.com");
        firstBuyerRegisterRequest
                .setAddress("312 Herbert Macaulay Way, Sabo-Yaba");
        firstBuyerRegisterRequest.setPassword("ademusa22@");
        firstBuyerRegisterRequest.setPhoneNumber("09011122244");

        secondBuyerRegisterRequest = new BuyerRegistrationRequest();
        secondBuyerRegisterRequest.setEmail("chikodiumar115@yahoo.com");
        secondBuyerRegisterRequest.setPassword("chikodi-11");
        secondBuyerRegisterRequest.setPhoneNumber("08022334455");
        secondBuyerRegisterRequest
                .setAddress("313 Herbert Macaulay Way, Sabo-Yaba");

        productPurchaseRequest=new ProductPurchaseRequest();
        productPurchaseRequest.setProductId(1);
        productPurchaseRequest.setQuantity(2);


        addProductRequest=new AddProductRequest();
        addProductRequest.setCategory("beverages");
        addProductRequest.setName("Milo");
        addProductRequest.setPrice(20.00);

    }

    @Test
    void registerTest() {
        BuyerRegistrationResponse response =
                buyerService.register(firstBuyerRegisterRequest);
        System.out.println(response);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), 201);
    }

    @Test
    void userWithInvalidDetailsGetsExceptionWhenRegisteringTest(){
        assertThrows(BuyerRegistrationException.class,
                ()->buyerService.register(secondBuyerRegisterRequest));
    }

    @Test
    void orderProductTest() {
        var addProductResponse =
                productService.addProduct(addProductRequest);
        assertNotNull(addProductResponse);
        assertEquals(201, addProductResponse.getStatusCode());
        String response =
                buyerService.orderProduct(productPurchaseRequest);
        assertNotNull(response);
    }
}