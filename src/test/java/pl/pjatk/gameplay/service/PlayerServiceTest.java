package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private DamageService damageService;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerService playerService;

    @AfterEach
    void cleanUp() {
        playerService.deleteAll();
    }

    @Test
    void findAll() {
        // given
        when(playerRepository.findAll()).thenReturn(List.of(new Player()));

        //when
        List<Player> listPlayers = playerService.findAll();
        //then
        assertThat(listPlayers).hasSize(1);
    }

    @Test
    void findById() {
        // given
        Optional<Player> lookedPlayer = Optional.of(new Player( 1L, "testPlayer",10,100));
        when(playerRepository.findById(1L)).thenReturn(lookedPlayer);
        // when
        Optional<Player> player = playerService.findById(1L);
        // then
        assertThat(player).isEqualTo(lookedPlayer);
    }

    @Test
    void savePlayer() {
        // given
        Player player1 = new Player("player",10,100, List.of());
        Player player2 = new Player(1L,"player",10,100);
        // when
        when(playerRepository.save(player1)).thenReturn(player2);
        // then
        assertThat(playerService.savePlayer(player1).getId()).isEqualTo(player2.getId());
    }

    @Test
    void shouldDelete() {
        // given

        // when
        playerService.deletePlayer(1L);
        playerService.deletePlayer(1L);
        playerService.deletePlayer(2L);
        playerService.deletePlayer(1L);
        // then
        verify(playerRepository,times(4)).deleteById(anyLong());
    }

    @Test
    void attack() {
        // given
        Player player1 = new Player(1l,"attacker",10,100);
        Player player2 = new Player(2l,"defender",10,100);
        when(damageService.defend(any(),any())).thenCallRealMethod();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(player2));
        when(playerRepository.save(player2)).thenReturn(player2);
        // when
        playerService.attack(player1.getId(), player2.getId());
        // then
        assertThat(player2.getHealth()).isEqualTo(90);
    }
}