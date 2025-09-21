package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private Map<UUID, Integer> products = new HashMap<>();

    public void add(UUID id) {
        if (products.putIfAbsent(id, 1) != null) {
            products.computeIfPresent(id, (key, value) -> value + 1);
        }
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}
