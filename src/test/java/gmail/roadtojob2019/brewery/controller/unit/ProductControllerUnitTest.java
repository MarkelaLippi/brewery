package gmail.roadtojob2019.brewery.controller.unit;

import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.Storage;
import gmail.roadtojob2019.brewery.entity.Type;
import gmail.roadtojob2019.brewery.entity.Unit;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ProductControllerUnitTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testGetPriselistIsOk() throws Exception {
        // given
        final Product product = getProductBeer();
        final List<Product> products = List.of(product);
        willReturn(products).given(productRepository).findByType(Type.BEER);
        String token = signInAsUser(UserRole.CUSTOMER);
        // when
        mockMvc.perform(get("/brewery/customer/pricelist").header("Authorization", token))
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
        verify(productRepository, times(1)).findByType(any(Type.class));
    }

    @Test
    void testGetAllProductsByTypeIsOk() throws Exception {
        // given
        final Product product = getProductBeer();
        final Storage storage = Storage.builder()
                .id(1L)
                .productId(1L)
                .amount(500.0)
                .build();
        product.setStorage(storage);
        final List<Product> products = List.of(product);
        willReturn(products).given(productRepository).findByType(Type.BEER);
        String token = signInAsUser(UserRole.SALES);
        // when
        mockMvc.perform(get("/brewery/sales/products?type=beer").header("Authorization", token))
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
        verify(productRepository, times(1)).findByType(any(Type.class));
    }

    private Product getProductBeer() {
        return Product.builder()
                .id(1L)
                .name("CoolBeer")
                .description("Light, 4.8% alcohol...")
                .price(new BigDecimal(2.5))
                .type(Type.BEER)
                .unit(Unit.LITRE)
                .build();
    }

    @Test
    void testGetProductByIdIsOk() throws Exception {
        // given
        final Optional<Product> requiredProduct = getProductIngredient();
        willReturn(requiredProduct).given(productRepository).findById(2L);
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(get("/brewery/brewer/products/2").header("Authorization", token))
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
        verify(productRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testGetProductByIdThrowsBrewerySuchProductNotFoundException() throws Exception {
        // given
        willReturn(Optional.empty()).given(productRepository).findById(2L);
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(get("/brewery/brewer/products/2").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Product with id = 2 was not found"));
        verify(productRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testGetProductsByIdsIsOk() throws Exception {
        // given
        final List<Long>ids=List.of(2L,3L);
        final List<Product> products = getProducts();
        willReturn(products).given(productRepository).findAllById(ids);
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(get("/brewery/brewer/products?ids=2,3").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 2, \n" +
                        "    \"name\" : \"Water\",\n" +
                        "    \"description\" : \"Artesian, ...\",\n" +
                        "    \"amount\" : 800.0,\n" +
                        "    \"unit\" : \"LITRE\" \n" +
                        "  },\n"+
                        "  {\n" +
                        "    \"id\" : 3, \n" +
                        "    \"name\" : \"Alcohol\",\n" +
                        "    \"description\" : \"Concentration 90%, ...\",\n" +
                        "    \"amount\" : 100.0,\n" +
                        "    \"unit\" : \"LITRE\" \n" +
                        "  }\n"+
                        "]"));
        verify(productRepository, times(1)).findAllById(ids);
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

    private List<Product> getProducts() {
        final Product product1 = Product.builder()
                .id(2L)
                .name("Water")
                .description("Artesian, ...")
                .type(Type.INGREDIENT)
                .unit(Unit.LITRE)
                .build();
        final Storage storage1 = Storage.builder()
                .id(2L)
                .productId(2L)
                .amount(800.0)
                .build();
        product1.setStorage(storage1);

        final Product product2 = Product.builder()
                .id(3L)
                .name("Alcohol")
                .description("Concentration 90%, ...")
                .type(Type.INGREDIENT)
                .unit(Unit.LITRE)
                .build();
        final Storage storage2 = Storage.builder()
                .id(3L)
                .productId(3L)
                .amount(100.0)
                .build();
        product2.setStorage(storage2);
        final ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        return products;
    }

    @Test
    public void testChangeProductAmountIsOk() throws Exception {
        // given
        final Product product = getProductBeer();
        final Storage storage = Storage.builder()
                .id(1L)
                .productId(1L)
                .amount(500.0)
                .build();
        product.setStorage(storage);
        final Optional<Product> requiredProduct = Optional.of(product);
        willReturn(requiredProduct).given(productRepository).findById(1L);
        willReturn(product).given(productRepository).save(any(Product.class));
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(patch("/brewery/brewer/products/1").header("Authorization", token)
                // then
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 250\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
        verify(productRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testChangeProductAmountThrowsBrewerySuchProductNotFoundException() throws Exception {
        // given
        final Product product = getProductBeer();
        willReturn(Optional.empty()).given(productRepository).findById(1L);
        willReturn(product).given(productRepository).save(any(Product.class));
        String token = signInAsUser(UserRole.BREWER);
        // when
        mockMvc.perform(patch("/brewery/brewer/products/1").header("Authorization", token)
                // then
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "    \"amount\" : 250\n" +
                        "  }\n"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Product with id = 1 was not found"));
        verify(productRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(0)).save(any(Product.class));
    }
}