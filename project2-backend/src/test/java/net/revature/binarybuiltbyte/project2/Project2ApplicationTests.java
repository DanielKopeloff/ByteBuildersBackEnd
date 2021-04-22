package net.revature.binarybuiltbyte.project2;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.revature.binarybuiltbyte.project2.repository.ByteUserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(ByteUserRepository.class)
public class Project2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("_links")));
    }
}






