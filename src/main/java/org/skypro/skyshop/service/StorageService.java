package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();

        fillWithDummyData();
    }

    private void fillWithDummyData() {
        this.productStorage.putAll(Map.of(
                UUID.randomUUID(), new SimpleProduct("Телевизор", 555, UUID.randomUUID()),
                UUID.randomUUID(), new SimpleProduct("Микроволновка", 3000, UUID.randomUUID()),
                UUID.randomUUID(), new SimpleProduct("Холодильник", 720, UUID.randomUUID()),
                UUID.randomUUID(), new FixPriceProduct("Фиксированный чайник", UUID.randomUUID()),
                UUID.randomUUID(), new DiscountedProduct("Скидочный тостер", 100, (byte) 5, UUID.randomUUID()),
                UUID.randomUUID(), new DiscountedProduct("Телевизор", 99, (byte) 5, UUID.randomUUID()))
        );

        this.articleStorage.putAll(Map.of(
                UUID.randomUUID(), new Article("Статья 1", "Текст статьи раз", UUID.randomUUID()),
                UUID.randomUUID(), new Article("Описание 2", "Текст описания два", UUID.randomUUID()))
        );
    }

    public Map<UUID, Product> getProductStorage() {
        return productStorage;
    }

    public Map<UUID, Article> getArticleStorage() {
        return articleStorage;
    }
}
