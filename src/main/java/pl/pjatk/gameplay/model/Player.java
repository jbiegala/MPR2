package pl.pjatk.gameplay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer damage;
    private Integer health;

    public Player() {
    }

    public Player(String name, Integer damage, Integer health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public Player(Long id, String name, Integer damage, Integer health) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
