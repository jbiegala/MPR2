package pl.pjatk.pozyczto.service;

import org.springframework.stereotype.Service;
import pl.pjatk.pozyczto.model.Rent;
import pl.pjatk.pozyczto.model.RentDTO;
import pl.pjatk.pozyczto.model.Product;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.repository.RentRepository;
import pl.pjatk.pozyczto.util.RentException;
import pl.pjatk.pozyczto.util.NotFoundEntityException;
import pl.pjatk.pozyczto.util.RentMapper;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private RentRepository rentRepository;
    private ProductService productService;
    private UserService userService;
    private RentMapper rentMapper;

    public RentService(RentRepository rentRepository, ProductService productService, UserService userService, RentMapper rentMapper) {
        this.rentRepository = rentRepository;
        this.productService = productService;
        this.userService = userService;
        this.rentMapper = rentMapper;
    }

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Optional<Rent> findById(Long id) {
        return rentRepository.findById(id);
    }

    public Optional<Product> findProductById(Long id) {
        return productService.findById(id);
    }

    public Optional<User> findUserById(Long id) {
        return userService.findById(id);
    }

    public Rent saveRent(Rent rent) {
        Optional<Product> dbProduct = productService.findById(rent.getProduct().getId());

        try {
            if (dbProduct.isPresent()) {
                try {
                    if (dbProduct.get().getActive()) {
                        try {
                            Long borrowerCoins = rent.getBorrower().getCoins();
                            if (borrowerCoins > getRentCoins(rent)) {
                                Long newBorrowerCoins = borrowerCoins - getRentCoins(rent);
                                User borrower = rent.getBorrower();
                                borrower.setCoins(newBorrowerCoins);
                                return rentRepository.save(rent);
                            } else {
                                throw new RentException("Not enough coins");
                            }
                        } catch (RentException re) {
                            System.out.println(re.getMessage());
                            return null;
                        }
                    } else {
                        throw new RentException("Inactive product");
                    }
                } catch (RentException re) {
                    System.out.println(re.getMessage());
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

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }

    public Rent updateRent(Long id, RentDTO rentDTO) {
        Optional<Rent> dbRent = rentRepository.findById(id);

        try {
            if (dbRent.isPresent()) {
                Optional<Product> dbProduct = productService.findById(dbRent.get().getProduct().getId());
                try {
                    if (dbProduct.isPresent()) {
                        Rent tmpRent = dbRent.get();
                        if (rentDTO.getEnded() && !tmpRent.getEnded()) {
                            Long borrowerCoins = tmpRent.getBorrower().getCoins();
                            Long newBorrowerCoins = borrowerCoins + getRentCoins(tmpRent);
                            User borrower = tmpRent.getBorrower();
                            borrower.setCoins(newBorrowerCoins);
                        }
                        rentMapper.updateRentFromDTO(rentDTO, tmpRent);
                        return rentRepository.save(tmpRent);
                    } else {
                        throw new NotFoundEntityException("Not found product");
                    }
                } catch (NotFoundEntityException nfee) {
                    System.out.println(nfee.getMessage());
                    return null;
                }
            } else {
                throw new NotFoundEntityException("Not found rent");
            }
        } catch (NotFoundEntityException nfee) {
            System.out.println(nfee.getMessage());
            return null;
        }
    }

    public Long getRentCoins(Rent rent) {
        Product product = rent.getProduct();
        Long productDuration = product.getDuration();
        Long rentDuration = Duration.between(rent.getDateStart().atStartOfDay(), rent.getDateEnd().atStartOfDay()).toDays();
        Long countPeriods = rentDuration/productDuration;
        return countPeriods * product.getPrice();
    }
}
