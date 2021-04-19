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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PaymentTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.payments.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.payments.[0].creditCard").exists())
                .andExpect(jsonPath("$.._embedded.payments.[0].expirationDateMonth").exists())
                .andExpect(jsonPath("$.._embedded.payments.[0].expirationDateYear").exists())
                .andExpect(jsonPath("$.._embedded.payments.[0].paymentCreated").exists())
                .andExpect(jsonPath("$.._embedded.payments.[0]._links.byte_user").exists());

    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/payment")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.payments.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.payments.[0].creditCard").value("865767859"))
                .andExpect(jsonPath("$.._embedded.payments.[0].expirationDateMonth").value("JULY"))
                .andExpect(jsonPath("$.._embedded.payments.[0].expirationDateYear").value("2025"))
                .andExpect(jsonPath("$.._embedded.payments.[0].paymentCreated").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.payments.[0]._links.byte_user.href").value("http://localhost/api/payment/1/byte_user"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/payment/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.creditCard").value("865767859"))
                .andExpect(jsonPath("$.expirationDateMonth").value("JULY"))
                .andExpect(jsonPath("$.expirationDateYear").value("2025"))
                .andExpect(jsonPath("$.paymentCreated").value("2021-04-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$._links.byte_user.href").value("http://localhost/api/payment/1/byte_user"));
    }

}
