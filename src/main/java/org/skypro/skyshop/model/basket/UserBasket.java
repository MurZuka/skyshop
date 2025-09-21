package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private List<BasketItem> userBasket;
    private int total;

    public UserBasket(List<BasketItem> userBasket) {
        this.userBasket = userBasket;
        this.total = userBasket.stream()
                .mapToInt(p -> p.getProduct().getCost() * p.getCount())
                .sum();
    }

    public List<BasketItem> getUserBasket() {
        return userBasket;
    }

    public int getTotal() {
        return total;
    }
}
