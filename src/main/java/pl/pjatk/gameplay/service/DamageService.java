package pl.pjatk.gameplay.service;

import pl.pjatk.gameplay.model.Player;

public class DamageService {

    static Player defend(Player defender, Player attacker) {

        int newHealth = defender.getHealth() - attacker.getDamage();
        if (newHealth < 0) {
            newHealth = 0;
        }
        defender.setHealth(newHealth);
        return defender;
    }
}
