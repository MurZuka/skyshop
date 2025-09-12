package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class SimpleProduct extends Product implements Searchable {
    private int cost;
    private final UUID id;

    public SimpleProduct(String name, int cost, UUID id) {
        super(name);

        if (cost > 0) {
            this.cost = cost;
        }

        this.id = id;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
