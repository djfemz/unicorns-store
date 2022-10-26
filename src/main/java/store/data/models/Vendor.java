package store.data.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Vendor extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String storeName;
    @ElementCollection
    private Set<String> storeAddresses=new HashSet<>();
}
