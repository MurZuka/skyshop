package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService() {
        this.storageService = new StorageService();
    }

    public Collection<Searchable> getStorage() {
        ArrayList<Searchable> result = new ArrayList<>();

        result.addAll(storageService.getArticleStorage().values());
        result.addAll(storageService.getProductStorage().values());

        return result;
    }

    public Collection<SearchResult> search(String toSearch) {
        Collection<Searchable> allSearchables = getStorage();

        return allSearchables.stream()
                .filter(p -> p.getSearchTerm().equalsIgnoreCase(toSearch))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }
}
