package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Player;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p from Player p where p.name = :name")
    Optional<Player> getPlayerByHisName(String name);

    Optional<Player> findByName(String name);

    @Query("SELECT p from Player p where p.damage = :damage")
    Optional<Player> getPlayerByHisAttack(String damage);

}
