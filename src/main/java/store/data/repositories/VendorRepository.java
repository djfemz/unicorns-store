package store.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.data.models.Vendor;

import java.util.List;


public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
