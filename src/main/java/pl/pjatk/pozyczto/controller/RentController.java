package pl.pjatk.pozyczto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.pozyczto.model.Rent;
import pl.pjatk.pozyczto.model.RentDTO;
import pl.pjatk.pozyczto.model.Product;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.service.RentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController {
    
    private RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Rent>> findAll() {
        return ResponseEntity.ok(rentService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Rent> findById(@PathVariable Long id) {
        Optional<Rent> byId = rentService.findById(id);
        if(byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/product/{productID}/borrower/{borrowerID}")
    public ResponseEntity<Rent> saveRent(@PathVariable Long productID,@PathVariable Long borrowerID,@RequestBody Rent rent) {
        Optional<Product> byProductId = rentService.findProductById(productID);
        Optional<User> byBorrowerId = rentService.findUserById(borrowerID);
        if (byProductId.isPresent() && byBorrowerId.isPresent()) {
            rent.setProduct(byProductId.get());
            rent.setBorrower(byBorrowerId.get());
            return ResponseEntity.ok(rentService.saveRent(rent));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rent> updateRent(@PathVariable Long id, @RequestBody RentDTO rentDTO) {
        Optional<Rent> byId = rentService.findById(id);
        Optional<Product> byProductId = rentService.findProductById(byId.get().getProduct().getId());
        Optional<User> byBorrowerId = rentService.findUserById(byId.get().getBorrower().getId());
        if(byId.isPresent() && byProductId.isPresent() && byBorrowerId.isPresent()) {
            return ResponseEntity.ok(rentService.updateRent(id, rentDTO));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
