package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

@Service
public class DamageService {

    public Player defend(Player defender, Player attacker) {

        int newHealth = defender.getHealth() - attacker.getDamage();
        if (newHealth < 0) {
            newHealth = 0;
        }
        defender.setHealth(newHealth);
        return defender;
    }

    public Player boostHealth(Player defender, Player attacker) {
        int boostHealth;
        if (defender.getHealth() > 50) {
            boostHealth = 10;
        } else {
            boostHealth = 5;
        }

        int newHealth = defender.getHealth() + boostHealth - attacker.getDamage();
        if (newHealth < 0) {
            newHealth = 0;
        }
        defender.setHealth(newHealth);
        return defender;
    }

    public Player boostAttack(Player defender, Player attacker) {
        int boostAttack;
        if (attacker.getDamage() > 15) {
            boostAttack = attacker.getDamage() * 3;
        } else {
            boostAttack = attacker.getDamage() * 6;
        }

        int newHealth = defender.getHealth() - boostAttack;
        if (newHealth < 0) {
            newHealth = 0;
        }
        defender.setHealth(newHealth);
        return defender;
    }

    public Player heal(Player player) {
        if (player.getHealth() > 50) {
            player.setHealth(player.getHealth() + 15);
        } else {
            player.setHealth(player.getHealth() + 5);
        }

        return player;
    }
}
