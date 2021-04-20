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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ByteOrder {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/byte-order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].status").exists())
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].orderCreated").exists())
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].orderCompleted").exists())
                .andExpect(jsonPath("$.._embedded.byteOrders.[0]._links.byteUser").exists())
                .andExpect(jsonPath("$.._embedded.byteOrders.[0]._links.productOrders").exists());

    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/byte-order")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].status").value("PENDING"))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].orderCreated").value("2021-04-12T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0].orderCompleted").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0]._links.byteUser.href").value("http://localhost/api/byte-order/1/byteUser"))
                .andExpect(jsonPath("$.._embedded.byteOrders.[0]._links.productOrders.href").value("http://localhost/api/byte-order/1/productOrders"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/byte-order/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.orderCreated").value("2021-04-12T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.orderCompleted").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$._links.byteUser.href").value("http://localhost/api/byte-order/1/byteUser"))
                .andExpect(jsonPath("$._links.productOrders.href").value("http://localhost/api/byte-order/1/productOrders"));
    }

}
