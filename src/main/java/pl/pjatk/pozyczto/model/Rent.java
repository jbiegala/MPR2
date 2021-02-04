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

}
