package pl.pjatk.pozyczto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne
    @JsonIgnore
    private Product product;

    @JoinColumn(name = "borrower_id")
    @OneToOne
    @JsonIgnore
    private User borrower;

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Boolean ended;

    public Rent() {
    }

    public Rent(Product product, User borrower, LocalDate dateStart, LocalDate dateEnd, Boolean ended) {
        this.product = product;
        this.borrower = borrower;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ended = ended;
    }

    public Rent(Long id, Product product, User borrower, LocalDate dateStart, LocalDate dateEnd, Boolean ended) {
        this.id = id;
        this.product = product;
        this.borrower = borrower;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ended = ended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }
}
