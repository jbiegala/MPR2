package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageServiceTest {

    private DamageService damageService = new DamageService();

    @Test
    void shouldDefendPlayer() {
        //given
        Player defender = new Player("test defender", 10, 100, List.of());
        Player attacker = new Player("test attacker", 10, 100, List.of());
        //when
        defender = damageService.defend(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(90);
    }

    @Test
    void shouldKillDefender() {
        //given
        Player defender = new Player("test defender", 10, 10, List.of());
        Player attacker = new Player("test attacker", 10, 100, List.of());
        //when
        defender = damageService.defend(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(0);
    }

    @Test
    void shouldHealthAfterDefendGreaterEqualZero() {
        //given
        Player defender = new Player("test defender", 10, 10, List.of());
        Player attacker = new Player("test attacker", 15, 100, List.of());
        //when
        defender = damageService.defend(defender, attacker);
        //then
        assertThat(defender.getHealth()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void shouldBoostHealthTen() {
        //given
        Player defender = new Player("test defender", 10, 100, List.of());
        Player attacker = new Player("test attacker", 10, 100, List.of());
        //when
        defender = damageService.boostHealth(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(100);
    }

    @Test
    void shouldBoostHealthFive() {
        //given
        Player defender = new Player("test defender", 10, 40, List.of());
        Player attacker = new Player("test attacker", 10, 100, List.of());
        //when
        defender = damageService.boostHealth(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(35);
    }

    @Test
    void shouldBoostAttackThreeTimes() {
        //given
        Player defender = new Player("test defender", 10, 100, List.of());
        Player attacker = new Player("test attacker", 20, 100, List.of());
        //when
        defender = damageService.boostAttack(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(40);
    }

    @Test
    void shouldBoostAttackSixTimes() {
        //given
        Player defender = new Player("test defender", 10, 100, List.of());
        Player attacker = new Player("test attacker", 10, 100, List.of());
        //when
        defender = damageService.boostAttack(defender, attacker);
        //then
        assertThat(defender.getHealth()).isEqualTo(40);
    }

    @Test
    void shouldHealPlayerByFifteen() {
        //given
        Player player = new Player("test player", 10, 51, List.of());
        //when
        player = damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(66);
    }

    @Test
    void shouldHealPlayerByFive() {
        //given
        Player player = new Player("test player", 10, 49, List.of());
        //when
        player = damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(54);
    }
}
