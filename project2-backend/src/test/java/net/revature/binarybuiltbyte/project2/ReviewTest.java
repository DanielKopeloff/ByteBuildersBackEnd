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
public class ReviewTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/review")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.reviews.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.reviews.[0].comment").exists())
                .andExpect(jsonPath("$.._embedded.reviews.[0].rating").exists())
                .andExpect(jsonPath("$.._embedded.reviews.[0].reviewCreated").exists())
                .andExpect(jsonPath("$.._embedded.reviews.[0].reviewTerminated").exists())
                .andExpect(jsonPath("$.._embedded.reviews.[0]._links.byteOrder").exists());

    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/review")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.reviews.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.reviews.[0].comment").value("I love this"))
                .andExpect(jsonPath("$.._embedded.reviews.[0].rating").value(5))
                .andExpect(jsonPath("$.._embedded.reviews.[0].reviewCreated").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.reviews.[0].reviewTerminated").isNotEmpty())
                .andExpect(jsonPath("$.._embedded.reviews.[0]._links.byteOrder.href").value("http://localhost/api/review/1/byteOrder"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/review/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comment").value("I love this"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.reviewCreated").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.reviewTerminated").value(nullValue()))
                .andExpect(jsonPath("$._links.byteOrder.href").value("http://localhost/api/review/1/byteOrder"));
    }
}
