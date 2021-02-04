package pl.pjatk.pozyczto.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long coins;
    private String district;
    private String city;
    private String county;
    private String email;
    private Long phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> productList = new ArrayList<>();

}
