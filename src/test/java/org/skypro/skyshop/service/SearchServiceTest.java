package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearchWithNoObjects() {
        // given
        Collection<Searchable> given = searchService.getStorage();
        given.clear();

        // when
        boolean result = given.isEmpty();

        // then
        Assertions.assertEquals(true, result);
    }

    @Test
    public void testSearchWithoutSuitableResult() {
        // given
        String searchPrompt = "Отсутствующий элемент";

        // when
        Collection<SearchResult> checking = searchService.search(searchPrompt);

        // then
        Assertions.assertEquals(true, checking.isEmpty());
    }

    @Test
    public void testSearchWithSuitableObject() {
        // given
        String searchPrompt = "Телевизор";

        // when
        Collection<SearchResult> checking = searchService.search(searchPrompt);

        // then
        Assertions.assertEquals(false, checking.isEmpty());
    }
}
