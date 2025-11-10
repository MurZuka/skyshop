package org.skypro.skyshop.service;

import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    // Добавление несуществующего товара в корзину приводит к выбросу исключения
    @Test
    void givenNonExistentProduct_whenAddToBasket_thenThrowException() {
        // given
        Product checkProduct = new SimpleProduct("Продукт, которого нет в хранилище", 500, UUID.randomUUID());

        // when
        Exception e = Assertions.assertThrows(NoSuchProductException.class, () -> basketService.add(checkProduct.getId()));

        // then
        Assertions.assertEquals(false, e == null);
    }

    // Добавление существующего товара вызывает метод addProduct у мока ProductBasket.
    @Test
    void givenExistentProduct_whenAddToBasket_thenAddProduct() {
        // given
        UUID existingId = UUID.randomUUID();
        Product product = new SimpleProduct("Test product", 100, existingId);

        // when
        when(storageService.getProductById(existingId)).thenReturn(Optional.of(product));
        basketService.add(existingId);

        // then
        verify(productBasket).add(existingId);
    }

   //    Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст.
    @Test
    void givenEmptyBasket_whenGetUserBasket_thenReturnEmptyBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertTrue(userBasket.getUserBasket().isEmpty());
        assertEquals(0, userBasket.getTotal());
    }

    //    Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
    @Test
    void givenLoadedBasket_whenGetUserBasket_thenReturnNotEmptyBasket() {
        UUID existingId = UUID.randomUUID();
        Product product = new SimpleProduct("Test product", 100, existingId);

        Map<UUID, Integer> productsMap = Collections.singletonMap(existingId, 5);
        when(productBasket.getProducts()).thenReturn(productsMap);
        when(storageService.getProductById(existingId)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();
        assertNotNull(userBasket);
        assertEquals(1, userBasket.getUserBasket().size());
        assertEquals(500, userBasket.getTotal());

        BasketItem basketItem = userBasket.getUserBasket().get(0);
        assertEquals(product, basketItem.getProduct());
        assertEquals(5, basketItem.getCount());
    }
}
