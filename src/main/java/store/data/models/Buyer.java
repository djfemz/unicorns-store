package store.data.models;

import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
public class Buyer extends User{
    private int id;
    private String firstname;
    private String lastname;
    private Set<String> deliveryAddresses=new TreeSet<>();
}
