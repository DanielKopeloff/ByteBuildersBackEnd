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
public class ProductOrderTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/product-order")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.productId").exists())
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.byteOrderId").exists())
                .andExpect(jsonPath("$.._embedded.productOrders.[0].quantity").exists())
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.productOrder").exists())
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.byteOrder").exists())
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.product").exists());

    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/product-order")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.productId").value(1))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.byteOrderId").value(1))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].quantity").value(2))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.productOrder.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)"))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.byteOrder.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)/byteOrder"))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.product.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)/product"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/product-order/?productid=2&byteOrderId=1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.productId").value(1))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].id.byteOrderId").value(1))
                .andExpect(jsonPath("$.._embedded.productOrders.[0].quantity").value(2))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.productOrder.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)"))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.byteOrder.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)/byteOrder"))
                .andExpect(jsonPath("$.._embedded.productOrders.[0]._links.product.href").value("http://localhost/api/product-order/ProductOrderId(productId=1,%20byteOrderId=1)/product"));;
    }
}
