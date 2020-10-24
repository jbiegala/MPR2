package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    public List<Player> findAll() {
//        List<Player> playerList = new ArrayList<>();
//        Player playerA = new Player();
//        Player playerB = new Player();
//        playerList.add(playerA);
//        playerList.add(playerB);
//
//        return playerList;

        return List.of(
                new Player(1,"Player One", 10, 100),
                new Player(2,"Player Two", 10, 100)
        );
    }
}
