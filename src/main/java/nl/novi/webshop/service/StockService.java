package nl.novi.webshop.service;

import nl.novi.webshop.model.Stock;

public interface StockService {
    public abstract Iterable<Stock> getStocks();

    public abstract Stock getStock(long id);

    public abstract Long addStock(Stock stock);

    public abstract void deleteStock(long id);

    public abstract void updateStock(long id, Stock stock);

    public abstract void takeOfStock(String productName, int amount);
}
