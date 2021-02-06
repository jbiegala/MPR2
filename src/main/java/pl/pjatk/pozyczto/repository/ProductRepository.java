package pl.pjatk.pozyczto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.pozyczto.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
