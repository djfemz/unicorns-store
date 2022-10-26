package store.data.models;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class User {
    private String email;
    private String password;
    private String phoneNumber;
}
