package store.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Buyer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BuyerRepositoryImplTest {
    private final BuyerRepository buyerRepository
            = new BuyerRepositoryImpl();
    private Buyer buyer;
    private Buyer secondBuyer;

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
        buyer.setFirstname("Godman");
        buyer.setLastname("Buhari");
        buyer.setEmail("emilokan2022@gmail.com");
        buyer.setPassword("IloveNaija2022");

        secondBuyer = new Buyer();
        secondBuyer.setFirstname("Jennifer");
        secondBuyer.setLastname("Emilokan");
        secondBuyer.setEmail("jenny123@gmail.com");
        secondBuyer.setPassword("jenny1234@");
    }

//

    @Test
    void saveTest() {
        //before save
        List<Buyer> buyersList = buyerRepository.findAll();
        assertEquals(0, buyer.getId());
        assertEquals(0, buyersList.size());
        //save buyer
        Buyer savedBuyer = buyerRepository.save(buyer);
        //buyer has id
        assertEquals(1, savedBuyer.getId());
        //there is one user in db
        buyersList=buyerRepository.findAll();
        assertEquals(1, buyersList.size());

        //save second buyer
        Buyer savedSecondBuyer =
                buyerRepository.save(secondBuyer);
       //second buyer's id is 2
        assertEquals(2, savedSecondBuyer.getId());
        //there are two buyers in the db
        buyersList = buyerRepository.findAll();
        assertEquals(2, buyersList.size());
    }

    @Test
    void findByIdTest() {
        Buyer firstSavedBuyer = buyerRepository.save(buyer);
        Buyer secondSavedBuyer = buyerRepository.save(secondBuyer);

        Buyer foundBuyer =
                buyerRepository.findById(secondSavedBuyer.getId());
        assertEquals(foundBuyer, secondSavedBuyer);
    }

    @Test
    void findAllTest() {
        buyerRepository.save(buyer);
        var listOfAllBuyersInDb =
                buyerRepository.findAll();
        assertEquals(1, listOfAllBuyersInDb.size());
        ;
    }

    @Test
    void deleteTest() {
        Buyer savedBuyer=buyerRepository.save(buyer);
        buyerRepository.delete(savedBuyer);
        assertFalse(buyerRepository.findAll().contains(savedBuyer));

    }

    @Test
    void deleteAllTest(){
        buyerRepository.save(buyer);
        assertEquals(1, buyerRepository.findAll().size());
        buyerRepository.deleteAll();
        assertEquals(0, buyerRepository.findAll().size());
    }
}