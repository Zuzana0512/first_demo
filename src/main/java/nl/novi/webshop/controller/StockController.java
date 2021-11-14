package nl.novi.webshop.controller;

import nl.novi.webshop.model.Stock;
import nl.novi.webshop.service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class StockController {

    @Autowired
    private StockServiceImpl stockService;

    @GetMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Stock> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Stock getStock (@PathVariable long id) {
        return stockService.getStock(id);
    }

    @PostMapping(value = "/stocks")
    public ResponseEntity addStock(@RequestBody Stock stock){
        Long newId = stockService.addStock(stock);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }

    @DeleteMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteStock (@PathVariable long id) {
        stockService.deleteStock(id);
        return "Deleted";
    }

    @PutMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateStock (@PathVariable long id, @RequestBody Stock stock){
        stockService.updateStock(id,stock);
        return "Updated";
    }
}
