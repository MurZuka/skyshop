package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class FixPriceProduct extends Product implements Searchable {
    private static final int FIXED_PRICE = 100;
    private final UUID id;

    public FixPriceProduct(String name, UUID id) {
        super(name);
        this.id = id;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getCost() {
        return FIXED_PRICE;
    }

    public String toString() {
        return this.getName() + ": " + this.getCost();
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
