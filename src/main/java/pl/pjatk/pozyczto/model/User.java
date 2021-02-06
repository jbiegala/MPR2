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

    public User() {
    }

    public User(String name, Long coins, String district, String city, String county, String email, Long phone, List<Product> productList) {
        this.name = name;
        this.coins = coins;
        this.district = district;
        this.city = city;
        this.county = county;
        this.email = email;
        this.phone = phone;
        this.productList = productList;
    }

    public User(Long id, String name, Long coins, String district, String city, String county, String email, Long phone) {
        this.id = id;
        this.name = name;
        this.coins = coins;
        this.district = district;
        this.city = city;
        this.county = county;
        this.email = email;
        this.phone = phone;
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

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
