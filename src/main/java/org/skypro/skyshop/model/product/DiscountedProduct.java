package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class DiscountedProduct extends Product implements Searchable {
    private int basePrice;
    private byte discountPercent;
    private final UUID id;

    public DiscountedProduct(String name, int basePrice, byte discountPercent, UUID id) {
        super(name);

        if (basePrice >= 0 && basePrice <= 100) {
            this.basePrice = basePrice;
        } else {
            throw new IllegalArgumentException("Некорректная базовая цена");
        }

        this.discountPercent = discountPercent;
        this.id = id;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getDiscountPercent() {
        return (int) this.discountPercent;
    }

    @Override
    public int getCost() {
        return basePrice - (basePrice * (discountPercent / 100));
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getCost() + " (" + this.getDiscountPercent() + "%)";
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
