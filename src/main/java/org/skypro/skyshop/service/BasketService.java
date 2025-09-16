package org.skypro.skyshop.service;

import org.apache.catalina.User;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void add(UUID id) {
        if (storageService.getProductById(id).isPresent() == false) {
            throw new IllegalArgumentException("Товара с ID " + id + " нет в каталоге");
        };

        basket.add(id);
    }

    public UserBasket getUserBasket() {
        UserBasket result = new UserBasket(basket.getProducts().entrySet().stream()
                .map(p -> new BasketItem(storageService.getProductById(p.getKey()).orElseThrow(() -> new RuntimeException()),
                                         p.getValue()))
                .toList());

        return result;
    }
}

