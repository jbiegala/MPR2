package pl.pjatk.pozyczto.service;

import org.springframework.stereotype.Service;
import pl.pjatk.pozyczto.model.Product;
import pl.pjatk.pozyczto.model.ProductDTO;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.repository.ProductRepository;
import pl.pjatk.pozyczto.util.NotFoundEntityException;
import pl.pjatk.pozyczto.util.ProductMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, UserService userService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.productMapper = productMapper;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<User> findUserById(Long id) {
        return userService.findById(id);
    }

    public Product saveProduct(Product product) {
        Optional<User> dbUser = userService.findById(product.getUser().getId());

        try {
            if (dbUser.isPresent()) {
                return productRepository.save(product);
            } else {
                throw new NotFoundEntityException("Not found user");
            }
        } catch (NotFoundEntityException nfee) {
            System.out.println(nfee.getMessage());
            return null;
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> dbProduct = productRepository.findById(id);

        try {
            if (dbProduct.isPresent()) {
                Optional<User> dbUser = userService.findById(dbProduct.get().getUser().getId());
                try {
                    if (dbUser.isPresent()) {
                        Product tmpProduct = dbProduct.get();
                        productMapper.updateProductFromDTO(productDTO, tmpProduct);
                        return productRepository.save(tmpProduct);
                    } else {
                        throw new NotFoundEntityException("Not found user");
                    }
                } catch (NotFoundEntityException nfee) {
                    System.out.println(nfee.getMessage());
                    return null;
                }
            } else {
                throw new NotFoundEntityException("Not found product");
            }
        } catch (NotFoundEntityException nfee) {
            System.out.println(nfee.getMessage());
            return null;
        }
    }
}
