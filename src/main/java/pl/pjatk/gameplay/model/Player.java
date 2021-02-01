package pl.pjatk.gameplay.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer damage;
    private Integer health;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<Message> messageList = new ArrayList<>();


    public Player() {
    }

    public Player(String name, Integer damage, Integer health, List<Message> messageList) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.messageList = messageList;
    }

    public Player(Long id, String name, Integer damage, Integer health) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

//    public Player(Long id, String name, Integer damage, Integer health, List<Message> messageList) {
//        this.id = id;
//        this.name = name;
//        this.damage = damage;
//        this.health = health;
//        this.messageList = messageList;
//    }

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

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
