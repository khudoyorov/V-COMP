package com.example.vcomp.repository;

import com.example.vcomp.dto.BrandDto;
import com.example.vcomp.dto.CommonDto;
import com.example.vcomp.model.Category;
import com.example.vcomp.model.Product;
import com.example.vcomp.service.mapper.BrandMapper;
import com.example.vcomp.service.mapper.CategoryMapper;
import com.example.vcomp.service.mapper.ProductMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductCustomRepository {
    private final EntityManager entityManager;
    private final ProductMapper productMapper;
    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<Product> universalSearch(String query, List<String> filter, String sorting, String ordering, Integer size, Integer currentPage) {
        size = Math.max(size, 0);
        int page = Math.max(currentPage,0);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);

        Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%");
        Predicate categoryPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("category").get("name")), "%" + query.toLowerCase() + "%");
        Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + query.toLowerCase() + "%");
        Predicate finalPredicate = criteriaBuilder.or(namePredicate, categoryPredicate, descriptionPredicate);



        TypedQuery<Category> customQuery = entityManager.createQuery("select c from Category c where LOWER( c.name ) LIKE :query", Category.class);
        customQuery.setParameter("query","%"+query.toLowerCase()+"%");

        if(filter != null && !filter.isEmpty()){
            Predicate filterPredicate = root.get("brand").get("name").in(filter);
            finalPredicate = criteriaBuilder.and(filterPredicate,filterPredicate);
            criteriaQuery.where(finalPredicate);
        }else {
            criteriaQuery.where(finalPredicate);
        }
        if (sorting != null && ordering != null) {
            Path<Object> sortPath;
            if (ordering.equalsIgnoreCase("ascending")) {
                sortPath = root.get(sorting);
                criteriaQuery.orderBy(criteriaBuilder.asc(sortPath));
            } else if (ordering.equalsIgnoreCase("descending")) {
                sortPath = root.get(sorting);
                criteriaQuery.orderBy(criteriaBuilder.desc(sortPath));
            }
        }

        TypedQuery<Product> search = entityManager.createQuery(criteriaQuery);

        long count = search.getResultList().size();


        if (count > 0 && count / size <= page){
            if (count % size == 0) {
                page = (int) count / size - 1;
            } else {
                page = (int) count / size;
            }
        }
        search.setFirstResult(size * page);
        search.setMaxResults(size);

        if(search.getResultList().isEmpty()){
            if(!customQuery.getResultList().isEmpty()){
                return null;//getWithSort(customQuery.getResultList().get(0).getId(),filter,sorting,ordering,currentPage);
            }
        }


        return new PageImpl<>(search.getResultList(), PageRequest.of(page,size),count);

    }

    @Override
    public CommonDto getWithSort(Integer cid, List<String> filter, String sorting, String ordering, Integer currentPage) {
        int size = 10;
        int page = Math.max(currentPage,0);

        List<Integer> categoryIds = getCategoriesWithChildren(cid);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate categoryPredicate = root.get("category").get("id").in(categoryIds);
        if(filter != null && !filter.isEmpty()){
            Predicate brandPredicate = root.get("brand").get("name").in(filter);
            criteriaQuery.select(root).where(criteriaBuilder.and(categoryPredicate,brandPredicate));
        }else {
            criteriaQuery.select(root).where(categoryPredicate);
        }


        if(sorting != null && ordering != null){
            Path<Object> sortPath = root.get(sorting);
            Order order = ordering.equalsIgnoreCase("ascending") ? criteriaBuilder.asc(sortPath) : criteriaBuilder.desc(sortPath);
            criteriaQuery.orderBy(order);
        }

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        long count = query.getResultList().size();
        if (count > 0 && count / size <= page) {
            if (count % size == 0) {
                page = (int) count / size - 1;
            } else {
                page = (int) count / size;
            }
        }
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        CommonDto response = new CommonDto();


        PageImpl<Product> products = new PageImpl<>(query.getResultList(), PageRequest.of(page, size), count);
        response.setProducts(products.map(pr->productMapper.toDto(pr)));
        Set<BrandDto> brands = query.getResultList().stream().map(pr -> brandMapper.toDto(pr.getBrand())).collect(Collectors.toSet());
        response.setBrands(brands);

        response.setCategories(getCategory(cid).stream().map(category -> categoryMapper.toDto(category)).toList());

        return response;
    }

    @Override
    @Transactional
    public boolean insertViewedProduct(Integer userId, Integer productId) {
        Query query = entityManager.createNativeQuery("INSERT INTO viewed_products(user_id, product_id) VALUES (:uId, :pId)")
                .setParameter("uId", userId)
                .setParameter("pId", productId);

        try {
            int i = query.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            log.error("error during insert viewed_product table {}",e.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> getCategory(Integer id) {
        List<Integer> categoriesId = getCategoriesWithChildrenAndParents(id);
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c where c.id in :category", Category.class)
                .setParameter("category",categoriesId);


        List<Category> categories = query.getResultList();
        return categories;
    }
    private List<Integer> getCategoriesWithChildren(Integer categoryId) {

        List<Integer> categoryIds = entityManager.createNativeQuery(
                        "WITH RECURSIVE child_categories AS (" +
                                "  SELECT id " +
                                "  FROM category " +
                                "  WHERE id = :categoryId " +
                                "  UNION ALL " +
                                "  SELECT c.id " +
                                "  FROM category c " +
                                "  JOIN child_categories cc ON cc.id = c.parent_category_id" +
                                ") " +
                                "SELECT id " +
                                "FROM child_categories"
                )
                .setParameter("categoryId", categoryId)
                .getResultList();

        return categoryIds;
    }
    private List<Integer> getCategoriesWithChildrenAndParents(Integer categoryId) {
        List<Integer> categoryIds = entityManager.createNativeQuery(
                        "WITH RECURSIVE parent_categories AS (\n" +
                                "  SELECT id, name, parent_category_id\n" +
                                "  FROM category\n" +
                                "  WHERE id=:categoryId\n" +
                                "  UNION\n" +
                                "  SELECT c.id, c.name, c.parent_category_id\n" +
                                "  FROM category c\n" +
                                "  INNER JOIN parent_categories pc ON c.id = pc.parent_category_id \n" +
                                ")\n" +
                                "SELECT id\n" +
                                "FROM parent_categories "
                )
                .setParameter("categoryId", categoryId)
                .getResultList();


        return categoryIds;
    }
//    public List<Integer> getCategoriesWithChildren(Integer categoryId) {
//        List<Integer> categoryIds = new ArrayList<>();
//        getCategoriesRecursive(categoryId, categoryIds);
//        return categoryIds;
//    }
//
//    private void getCategoriesRecursive(Integer categoryId, List<Integer> categoryIds) {
//        categoryIds.add(categoryId);
//
//        List<Integer> childIds = entityManager.createQuery(
//                        "SELECT c.id FROM Category c WHERE c.parent_category_id = :categoryId",
//                        Integer.class
//                )
//                .setParameter("categoryId", categoryId)
//                .getResultList();
//
//        for (Integer childId : childIds) {
//            getCategoriesRecursive(childId, categoryIds);
//        }
//    }

}