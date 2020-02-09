package gmail.roadtojob2019.brewery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/brewery/brewer")
public class BrewerController {

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody String request) {
        return "{\"id\":1}";
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public String getAllRequests() {
        return "[\n" +
                "  {\n" +
                "  \"date\" : \"05.02.2020\",\n" +
                "  \"name_beer\" : \"BudBeer\",\n" +
                "  \"amount\" : \"200\",\n" +
                "  \"term\" : \"10.02.2020\",\n" +
                "  \"status\" : \"new\"\n" +
                "  }\n" +
                "]";
    }

    @GetMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getRecipe(@PathVariable String id) {
        return "[\n" +
                "  {\n" +
                "  \"water\" : \"300\",\n" +
                "  \"alcohol\" : \"15\",\n" +
                "  \"malt\" : \"10\"\n" +
                "  },\n" +
                "  {\n" +
                "  \"beer_id\" : \"1\"\n" +
                "  }\n" +
                "]";
    }

    @GetMapping("/ingredients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getIngredient(@PathVariable String id) {
        return "  {\n" +
                "  \"name\" : \"malt\",\n" +
                "  \"amount\" : \"80\"\n" +
                "  }\n";
    }

    @PutMapping(value = "/requests/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String changeRequestStatus(@RequestBody String request) {
        return "{\"id\":1}";
    }

    @PutMapping(value = "/beers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String changeBeerAmount(@RequestBody String request) {
        return "{\"id\":1}";
    }
}
