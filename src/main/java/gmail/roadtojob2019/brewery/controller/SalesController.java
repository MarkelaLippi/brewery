package gmail.roadtojob2019.brewery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/brewery/sales")
public class SalesController {

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody String request) {
        return "{\"id\":1}";
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public String getAllOrders() {
        return "[\n" +
                "  {\n" +
                "    \"id\" : 1, \n" +
                "    \"date\" : \"05.02.2020\",\n" +
                "    \"name_beer\" : \"BudBeer\",\n" +
                "    \"amount\" : \"200\",\n" +
                "    \"customer_id\" : \"1\" \n" +
                "  }\n" +
                "]";
    }

    @GetMapping("/beers")
    @ResponseStatus(HttpStatus.OK)
    public String getAllBeers() {
        return "[\n" +
                "  {\n" +
                "    \"id\" : 1, \n" +
                "    \"name\" : \"CoolBeer\",\n" +
                "    \"type\" : \"Светлое\",\n" +
                "    \"alcohol\" : \"4,8%\",\n" +
                "    \"amount\" : \"2540\",\n" +
                "    \"recipe\" : \"water, spirit…\" \n" +
                "  }\n" +
                "]";
    }

    @PostMapping(value = "/request", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String makeRequest(@RequestBody String request) {
        return "{\"id\":1}";
    }
}
