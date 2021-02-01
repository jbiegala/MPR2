package pl.pjatk.gameplay.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
//    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/player"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/player/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world")));
    }

    @Test
    void shouldNotFindPlayerById() throws Exception {
        mockMvc.perform(get("/player/10000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindPlayerById() throws Exception {
//        playerRepository.save(new Player("test",10,100));
        when(playerRepository.findById(1L))
                .thenReturn((Optional.of(new Player(1L,"test",10,100))));

        mockMvc.perform(get("/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"test\",\"damage\":10,\"health\":100,\"messageList\":[]}"));
    }
}