package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.*;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testGetPriselistIsOk() throws Exception {
        // given
        // signInAsCustomer();
        final Product product = getProductBeer();

        final List<Product> products = List.of(product);

        willReturn(products).given(productRepository)
                .findByType(Type.BEER);
        // when
        mockMvc.perform(get("/brewery/customer/pricelist"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"beerName\" : \"CoolBeer\",\n" +
                        "    \"beerDescription\" : \"Light, 4.8% alcohol...\",\n" +
                        "    \"price\" : 2.5\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    void testGetAllProductsByTypeIsOk() throws Exception {
        // given
        // signInAsCustomer();
        final Product product = getProductBeer();

        final Storage storage = Storage.builder()
                .id(1L)
                .productId(1L)
                .amount(500.0)
                .build();

        product.setStorage(storage);

        final List<Product> products = List.of(product);

        willReturn(products).given(productRepository)
                .findByType(Type.BEER);
        // when
        mockMvc.perform(get("/brewery/sales/products?type=beer"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"CoolBeer\",\n" +
                        "    \"description\" : \"Light, 4.8% alcohol...\",\n" +
                        "    \"price\" : 2.5,\n" +
                        "    \"amount\" : 500.0,\n" +
                        "    \"unit\" : \"LITRE\" \n" +
                        "  }\n" +
                        "]"));
    }

    private Product getProductBeer() {
        return Product.builder()
                .id(1L)
                .name("CoolBeer")
                .description("Light, 4.8% alcohol...")
                .price(2.5)
                .type(Type.BEER)
                .unit(Unit.LITRE)
                .build();
    }

    @Test
    void testGetProductByIdIsOk() throws Exception {
        // given
        // signInAsCustomer();
        final Optional<Product> requiredProduct = getProductIngredient();

        willReturn(requiredProduct).given(productRepository)
                .findById(2L);
        // when
        mockMvc.perform(get("/brewery/brewer/products/2"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "  {\n" +
                                "    \"id\" : 2, \n" +
                                "    \"name\" : \"Water\",\n" +
                                "    \"description\" : \"Artesian, ...\",\n" +
                                "    \"amount\" : 800.0,\n" +
                                "    \"unit\" : \"LITRE\" \n" +
                                "  }\n"));
    }

    private Optional<Product> getProductIngredient() {
        final Product product = Product.builder()
                .id(2L)
                .name("Water")
                .description("Artesian, ...")
                .type(Type.INGREDIENT)
                .unit(Unit.LITRE)
                .build();

        final Storage storage = Storage.builder()
                .id(2L)
                .productId(2L)
                .amount(800.0)
                .build();

        product.setStorage(storage);

        return Optional.of(product);
    }

    @Test
    public void testChangeProductAmountIsOk() throws Exception {
        // given
        // signInAsCustomer();
        final Product product = getProductBeer();

        final Storage storage = Storage.builder()
                .id(1L)
                .productId(1L)
                .amount(500.0)
                .build();

        product.setStorage(storage);

        final Optional<Product> requiredProduct = Optional.of(product);

        willReturn(requiredProduct).given(productRepository)
                .findById(1L);

        willReturn(product).given(productRepository)
                .save(any(Product.class));
        // when
        mockMvc.perform(patch("/brewery/brewer/products/1")
                // then
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 250\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }
}