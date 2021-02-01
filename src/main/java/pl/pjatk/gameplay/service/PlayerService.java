package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Message;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private DamageService damageService;

    public PlayerService(PlayerRepository playerRepository, DamageService damageService) {
        this.playerRepository = playerRepository;
        this.damageService = damageService;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        if (id == 10L) {
            throw new RuntimeException();
        } else {
            return playerRepository.findById(id);
        }
    }

    public Optional<Player> findByName(String name) {
            return playerRepository.getPlayerByHisName(name);
    }

    public Player savePlayer(Player player) {
//        player.getMessageList().add(new Message("content 1", player));
//        player.getMessageList().add(new Message("content 2", player));
//        player.getMessageList().add(new Message("content 3", player));

        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public void deleteAll() {
        playerRepository.deleteAll();
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player attack(Long attackerId, Long defenderId) {

        Player attackerPlayer = playerRepository.findById(attackerId).get();
        Player defenderPlayer = playerRepository.findById(defenderId).get();

        defenderPlayer = damageService.defend(defenderPlayer, attackerPlayer);

        return playerRepository.save(defenderPlayer);
    }
}
