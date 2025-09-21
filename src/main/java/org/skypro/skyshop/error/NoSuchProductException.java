package org.skypro.skyshop.error;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Товар не найден");
    }
}
