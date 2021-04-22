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
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType("application", "hal+json");

    @Test
    public void checkReturningFields() throws Exception {
        this.mockMvc.perform(get("/api/product")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.products.[0].id").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].description").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].stock").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].price").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].sku").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].picture").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].rating").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].productCreated").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].productTerminated").exists())
                .andExpect(jsonPath("$.._embedded.products.[0].active").exists())
                .andExpect(jsonPath("$.._embedded.products.[0]._links.category").exists());


    }

    @Test
    public void checkPopulatingCorrectly() throws Exception {

        this.mockMvc.perform(get("/api/product")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.._embedded.products.[0].id").value(1))
                .andExpect(jsonPath("$.._embedded.products.[0].description").value("AMD RYZEN 7 3700X 8-Core 3.6 GHz (4.4 GHz Max Boost) Socket AM4 65W 100-100000071BOX Desktop Processor"))
                .andExpect(jsonPath("$.._embedded.products.[0].stock").value(20))
                .andExpect(jsonPath("$.._embedded.products.[0].price").value(319.99))
                .andExpect(jsonPath("$.._embedded.products.[0].sku").value("N82E16819113567"))
                .andExpect(jsonPath("$.._embedded.products.[0].picture").value("https://c1.neweggimages.com/ProductImageCompressAll1280/19-113-567-V11.jpg"))
                .andExpect(jsonPath("$.._embedded.products.[0].rating").value(3))
                .andExpect(jsonPath("$.._embedded.products.[0].productCreated").value("2021-10-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.._embedded.products.[0].productTerminated").isNotEmpty())
                .andExpect(jsonPath("$.._embedded.products.[0].active").value(true))
                .andExpect(jsonPath("$.._embedded.products.[0]._links.category.href").value("http://localhost/api/product/1/category"));
    }

    @Test
    public void checkReturnID1() throws Exception {

        this.mockMvc.perform(get("/api/product/1")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("AMD RYZEN 7 3700X 8-Core 3.6 GHz (4.4 GHz Max Boost) Socket AM4 65W 100-100000071BOX Desktop Processor"))
                .andExpect(jsonPath("$.stock").value(20))
                .andExpect(jsonPath("$.price").value(319.99))
                .andExpect(jsonPath("$.sku").value("N82E16819113567"))
                .andExpect(jsonPath("$.picture").value("https://c1.neweggimages.com/ProductImageCompressAll1280/19-113-567-V11.jpg"))
                .andExpect(jsonPath("$.rating").value(3))
                .andExpect(jsonPath("$.productCreated").value("2021-10-13T04:00:00.000+00:00"))
                .andExpect(jsonPath("$.productTerminated").value(nullValue()))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$._links.category.href").value("http://localhost/api/product/1/category"));
    }
}
