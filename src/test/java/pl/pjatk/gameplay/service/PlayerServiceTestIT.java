package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class PlayerServiceTestIT {

    @Autowired
    private PlayerService playerService;

    @AfterEach
    void cleanUp() {
        playerService.deleteAll();
    }

    @Test
    void shouldNotFindPlayers() {
        List<Player> all = playerService.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    void shouldFindPlayers() {
        Player player1 = playerService.savePlayer(new Player("name", 100,10, List.of()));
        Player player2 = playerService.savePlayer(new Player("name", 100,10, List.of()));
        Player player3 = playerService.savePlayer(new Player("name", 100,10, List.of()));

        List<Player> all = playerService.findAll();

        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldAddPlayer() {
        Player player = playerService.savePlayer(new Player("name", 100,10, List.of()));

        assertThat(player.getId()).isPositive();
    }

    @Test
    void shouldFindPlayer() {
        Player lookedPlayer = playerService.savePlayer(new Player("testPlayer",10,100, List.of()));

        assertThat(playerService.findById(lookedPlayer.getId())).isNotEmpty();
    }

    @Test
    void shouldThrowRuntimeException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> playerService.findById(10L));
    }

    @Test
    void shouldAttack() {
        Player attacker = playerService.savePlayer(new Player("attacker", 10,100, List.of()));
        Player defender = playerService.savePlayer(new Player("defender", 10,100, List.of()));

        playerService.attack(attacker.getId(),defender.getId());

        assertThat(playerService.findById(defender.getId()).get().getHealth()).isEqualTo(90);
    }
}
