package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.model.PlayerDTO;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        playerMapper = new PlayerMapper();
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public Player updatePlayer(Long id, PlayerDTO playerWithUpdatesProperties) {
        Player dbPlayer = playerRepository.findById(id).get();
        playerMapper.mapDtoToPlayer(dbPlayer, playerWithUpdatesProperties);
        return playerRepository.save(dbPlayer);
    }

    public Player attack(Long attackerId, Long defenderId) {

        Player attackerPlayer = playerRepository.findById(attackerId).get();
        Player defenderPlayer = playerRepository.findById(defenderId).get();

        defenderPlayer = DamageService.defend(defenderPlayer, attackerPlayer);

        return playerRepository.save(defenderPlayer);
    }
}
