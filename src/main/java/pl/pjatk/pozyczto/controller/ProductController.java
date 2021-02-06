package pl.pjatk.pozyczto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.pozyczto.model.Product;
import pl.pjatk.pozyczto.model.ProductDTO;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> byId = productService.findById(id);
        if(byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/user/{userID}")
    public ResponseEntity<Product> saveProduct(@PathVariable Long userID,@RequestBody Product product) {
        Optional<User> byUserId = productService.findUserById(userID);
        if (byUserId.isPresent()) {
            product.setUser(byUserId.get());
            return ResponseEntity.ok(productService.saveProduct(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> byId = productService.findById(id);
        if(byId.isPresent()) {
            return ResponseEntity.ok(productService.updateProduct(id, productDTO));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
