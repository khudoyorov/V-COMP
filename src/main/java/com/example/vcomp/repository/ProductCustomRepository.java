package com.example.vcomp.repository;

import java.util.List;

import com.example.vcomp.dto.CommonDto;
import com.example.vcomp.model.Category;
import com.example.vcomp.model.Product;
import org.springframework.data.domain.Page;

public interface ProductCustomRepository {
    Page<Product> universalSearch(String query, List<String> filter, String sorting, String ordering, Integer size, Integer currentPage);
    CommonDto getWithSort(Integer id, List<String> filter, String sorting, String ordering, Integer currentPage);
    boolean insertViewedProduct(Integer userId, Integer productId);
    List<Category> getCategory(Integer id);

}