package pl.pjatk.pozyczto.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.pozyczto.model.User;
import pl.pjatk.pozyczto.model.UserDTO;
import pl.pjatk.pozyczto.repository.UserRepository;
import pl.pjatk.pozyczto.util.UserMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;
    
    @AfterEach
    void cleanUp() {
        userService.deleteAll();
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        
        List<User> listUsers = userService.findAll();

        assertThat(listUsers).hasSize(1);
    }

    @Test
    void findById() {
        Optional<User> lookedUser = Optional.of(new User(1L,"test User", 10L, "district", "city", "county", "email", 654987123L));
        when(userRepository.findById(1L)).thenReturn(lookedUser);

        Optional<User> user = userService.findById(1L);

        assertThat(user).isEqualTo(lookedUser);
    }

    @Test
    void saveUser() {
        User user1 = new User("test User", 10L, "district", "city", "county", "email", 654987123L, List.of());
        User user2 = new User(1L,"test User", 10L, "district", "city", "county", "email", 654987123L);

        when(userRepository.save(user1)).thenReturn(user2);

        assertThat(userService.saveUser(user1).getId()).isEqualTo(user2.getId());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1L);
        userService.deleteUser(1L);
        userService.deleteUser(1L);
        userService.deleteUser(1L);
        userService.deleteUser(1L);

        verify(userRepository,times(5)).deleteById(anyLong());
    }

    @Test
    void updateUser() {
        User user1 = new User(1L,"test User", 10L, "district", "city", "county", "email", 654987123L);
        User user2 = new User(1L,"test User", 7L, "district", "city", "county", "email", 654987123L);
        UserDTO userDTO = new UserDTO("test User", 7L, "district", "city", "county", "email", 654987123L);
        Optional<User> lookedUser = Optional.of(user1);

        when(userRepository.findById(1L)).thenReturn(lookedUser);
//        when(userMapper.updateUserFromDTO(userDTO,user1)).thenReturn(user2);
        when(userRepository.save(user1)).thenReturn(user2);

        assertThat(userService.updateUser(1L,userDTO)).isEqualToComparingFieldByField(user2);
    }
}