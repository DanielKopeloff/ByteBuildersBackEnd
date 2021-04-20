package net.revature.binarybuiltbyte.project2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AddressTest {


    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/address"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.addresses.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.addresses.[0].streetAddress").exists())
                .andExpect(jsonPath("$.._embedded.addresses.[0].city").exists())
                .andExpect(jsonPath("$.._embedded.addresses.[0].state").exists())
                .andExpect(jsonPath("$.._embedded.addresses.[0].zip").exists())
                .andExpect(jsonPath("$.._embedded.addresses.[0]._links.byteUser").exists());

    }

//    @Test
//    public void postReturningFields() throws Exception {
//        Address ad = new Address(2 ,"sdfsfd" ,"sdfsdf" ,"sdfsdf" ,"sdf" ,new ByteUser());
//
//        this.mockMvc.perform(post("/api/address"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.._embedded.addresses.[0].id").exists())
//                .andExpect(jsonPath("$.._embedded.addresses.[0].streetAddress").exists())
//                .andExpect(jsonPath("$.._embedded.addresses.[0].city").exists())
//                .andExpect(jsonPath("$.._embedded.addresses.[0].state").exists())
//                .andExpect(jsonPath("$.._embedded.addresses.[0].zip").exists())
//                .andExpect(jsonPath("$.._embedded.addresses.[0]._links.byteUser").exists());
//
//    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/address")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.addresses.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.addresses.[0].streetAddress").value("333 Santa Fe Ave"))
                .andExpect(jsonPath("$.._embedded.addresses.[0].city").value("Alamosa"))
                .andExpect(jsonPath("$.._embedded.addresses.[0].state").value("CO"))
                .andExpect(jsonPath("$.._embedded.addresses.[0].zip").value("81101"))
                .andExpect(jsonPath("$.._embedded.addresses.[0]._links.byteUser.href").value("http://localhost/api/address/1/byteUser"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/address/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.streetAddress").value("333 Santa Fe Ave"))
                .andExpect(jsonPath("$.city").value("Alamosa"))
                .andExpect(jsonPath("$.state").value("CO"))
                .andExpect(jsonPath("$.zip").value("81101"))
                .andExpect(jsonPath("$._links.byteUser.href").value("http://localhost/api/address/1/byteUser"));
    }
}
