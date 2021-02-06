package pl.pjatk.pozyczto.service;

import org.springframework.stereotype.Service;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.model.UserDTO;
import pl.pjatk.pozyczto.repository.UserRepository;
import pl.pjatk.pozyczto.util.NotFoundEntityException;
import pl.pjatk.pozyczto.util.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findById(id);

        try {
            if (dbUser.isPresent()) {
                User tmpUser = dbUser.get();
                userMapper.updateUserFromDTO(userDTO, tmpUser);
                return userRepository.save(tmpUser);
            } else {
                throw new NotFoundEntityException("Not found user");
            }
        } catch (NotFoundEntityException nfee) {
            System.out.println(nfee.getMessage());
            return null;
        }
    }
}
