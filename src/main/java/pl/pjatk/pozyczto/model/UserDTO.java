package pl.pjatk.pozyczto.model;

public class UserDTO {
    private String name;
    private Long coins;
    private String district;
    private String city;
    private String county;
    private String email;
    private Long phone;

    public UserDTO(String name, Long coins, String district, String city, String county, String email, Long phone) {
        this.name = name;
        this.coins = coins;
        this.district = district;
        this.city = city;
        this.county = county;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO() {
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
}
