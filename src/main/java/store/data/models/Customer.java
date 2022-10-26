package store.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
public class Customer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    @ElementCollection
    private Set<String> deliveryAddresses=new TreeSet<>();
    @OneToMany
    private List<Product> orders = new ArrayList<>();
}
