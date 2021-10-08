package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Stock;
import nl.novi.first_demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class StockController {
    @Autowired
    private static StockService stockService;

    @GetMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Stock> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Stock getStock (@PathVariable int id) {
        return stockService.getStock(id);
    }

    @PostMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStock (@RequestBody Stock stock){
        stockService.addStock(stock);
        return "Added";
    }

    @DeleteMapping(value = "/stocks/{id}")
    public String deleteStock (@PathVariable int id) {
        stockService.deleteStock(id);
        return "Deleted";
    }

    @PutMapping(value = "/stocks/{id}")
    public String updateStock (@PathVariable int id, @RequestBody Stock stock){
        stockService.updateStock(id,stock);
        return "Updated";
    }
}
