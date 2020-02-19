package gmail.roadtojob2019.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ProduceRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSalesProduceRequestIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/sales/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"beerId\" : 1,\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"New\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("1"));
    }
}