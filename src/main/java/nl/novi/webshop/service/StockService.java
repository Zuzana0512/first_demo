package nl.novi.webshop.service;

import nl.novi.webshop.exeption.NotEnoughStockException;
import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Stock;
import nl.novi.webshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Iterable<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Stock getStock(long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            return stock.get();
        } else {
            throw new RecordNotFoundException("Stock with id " + " not found.");
        }

    }

    public Long addStock(Stock stock) {
        Stock newStock = stockRepository.save(stock);
        return newStock.getId();
    }

    public void deleteStock(long id) {
        if(stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException("Stock with id " + " not found.");
        }
    }

    public void updateStock(long id, Stock stock){
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isPresent()){
            Stock stockInDb = optionalStock.get();
            stockInDb.setProductName(stockInDb.getProductName());
            stockInDb.setProductAmount(stockInDb.getProductAmount());
            stockInDb.setSupplierName(stock.getSupplierName());
            stockRepository.save(stockInDb);
        }
        else{
            throw new RecordNotFoundException("Stock with id " + " not found.");
        }
    }

    public void takeOfStock(String productName, int amount){
        Optional<Stock> optionalStock = stockRepository.findByProductName(productName);
        if(optionalStock.isPresent()){
            Stock stockInDb = optionalStock.get();
            if(stockInDb.getProductAmount() < amount){
                throw new NotEnoughStockException("Not in stock.");
            }
            stockInDb.setProductAmount(stockInDb.getProductAmount() - amount);
            stockRepository.save(stockInDb);
        }
        else{
            throw new RecordNotFoundException("Stock with id " + " not found.");
        }
    }


}
