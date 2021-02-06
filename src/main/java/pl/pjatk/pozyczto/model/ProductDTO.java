package pl.pjatk.pozyczto.model;

public class ProductDTO {
    private String name;
    private String description;
    private Long price;
    private Long duration;
    private Boolean active;
    private User user;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, Long price, Long duration, Boolean active, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.active = active;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
   