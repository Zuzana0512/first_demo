package nl.novi.webshop.exeption;

public class NotEnoughStockException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

}

