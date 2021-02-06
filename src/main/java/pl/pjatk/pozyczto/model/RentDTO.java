package pl.pjatk.pozyczto.model;

import java.time.LocalDate;

public class RentDTO {
    private Product product;
    private User borrower;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Boolean ended;

    public RentDTO() {
    }

    public RentDTO(Product product, User borrower, LocalDate dateStart, LocalDate dateEnd, Boolean ended) {
        this.product = product;
        this.borrower = borrower;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ended = ended;
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
