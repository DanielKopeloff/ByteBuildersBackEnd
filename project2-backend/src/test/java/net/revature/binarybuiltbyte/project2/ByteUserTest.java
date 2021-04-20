package net.revature.binarybuiltbyte.project2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
//@DataJpaTest
public class ByteUserTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    // Case Sensitive
    @Test
    public void checkReturningFields() throws Exception {

        this.mockMvc.perform(get("/api/byte-user")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].username").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].password").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].firstName").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].lastName").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].email").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].profilePic").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].byteRole").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].userCreated").exists())
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].userTerminated").exists());
    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/byte-user")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].username").value("danielK"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].password").value("password"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].firstName").value("Daniel"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].lastName").value("Kop"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].email").value("danielKOP@gmail.com"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].profilePic").value("https://www.thedesignwork.com/wp-content/uploads/2011/10/Random-Pictures-of-Conceptual-and-Creative-Ideas-02.jpg"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].byteRole").value("USER"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].userCreated").value("2021-03-02T05:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.byteUsers.[0].userTerminated").isNotEmpty());
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/byte-user/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("danielK"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.firstName").value("Daniel"))
                .andExpect(jsonPath("$.lastName").value("Kop"))
                .andExpect(jsonPath("$.email").value("danielKOP@gmail.com"))
                .andExpect(jsonPath("$.profilePic").value("https://www.thedesignwork.com/wp-content/uploads/2011/10/Random-Pictures-of-Conceptual-and-Creative-Ideas-02.jpg"))
                .andExpect(jsonPath("$.byteRole").value("USER"))
                .andExpect(jsonPath("$.userCreated").value("2021-03-02T05:00:00.000+00:00"))
                .andExpect(jsonPath("$.userTerminated").value(nullValue()));
    }

}



