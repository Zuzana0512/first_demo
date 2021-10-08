package nl.novi.first_demo.service;

import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Stock;
import nl.novi.first_demo.repository.StockRepository;
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

    public void addStock(Stock stock) {
        stockRepository.save(stock);
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
            stockInDb.setProductNameSupplier(stock.getProductNameSupplier());
            stockInDb.setSupplierName(stock.getSupplierName());
            stockRepository.save(stockInDb);
        }
        else{
            throw new RecordNotFoundException("Stock with id " + " not found.");
        }
    }


}
