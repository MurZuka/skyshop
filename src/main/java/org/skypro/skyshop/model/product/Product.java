package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

public abstract class Product implements Searchable {
    private String name;

    public Product(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Некорректное имя продукта");
        }
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName() + ": " + getCost();
    }

    @Override
    public String getSearchTerm() {
        return this.getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Product) {
            if (((Product) obj).getName() == this.getName()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public abstract int getCost();

    public abstract boolean isSpecial();
}
