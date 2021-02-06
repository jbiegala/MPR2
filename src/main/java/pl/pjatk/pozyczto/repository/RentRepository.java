package pl.pjatk.pozyczto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.pozyczto.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
