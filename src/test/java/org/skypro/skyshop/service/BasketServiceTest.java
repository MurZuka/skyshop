package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.util.concurrent.MonoToListenableFutureAdapter;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenNonExistentProduct_whenAddToBasket_thenThrowException() {
        // given
        Product checkProduct = new SimpleProduct("Продукт, которого нет в хранилище", 500, UUID.randomUUID());

        // when
        Exception e = Assertions.assertThrows(NoSuchProductException.class, () -> basketService.add(checkProduct.getId()));

        // then
        Assertions.assertEquals(false, e == null);
    }

    @Test
    public void givenExistentProduct_whenAddToBasket_thenAddProduct() {
        UUID existingId = UUID.randomUUID();
//        Map<UUID, Product> mockedProductStorage = new HashMap<>();
        Product product = new SimpleProduct("Test product", 100, existingId);
//        mockedProductStorage.put(existingId, product);
        when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.of(product));
        //when(storageService.getProductStorage()).thenReturn(mockedProductStorage);

        basketService.add(existingId);

        verify(productBasket).add(existingId);
    }

    @Test
    public void givenEmptyBasket_whenGetUserBasket_thenReturnEmptyBasket() {

    }

    @Test
    public void givenLoadedBasket_whenGetUserBasket_thenReturnNotEmptyBasket() {

    }
}
