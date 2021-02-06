package pl.pjatk.pozyczto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.pozyczto.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
