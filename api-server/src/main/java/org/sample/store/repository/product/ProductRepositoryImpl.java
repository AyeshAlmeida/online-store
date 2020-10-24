package org.sample.store.repository.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Lazy(false)
public class ProductRepositoryImpl implements ProductRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private static final String FIND_PRODUCTS = "SELECT * FROM product";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> mapper;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ProductMapper();
    }

    @Override
    public List<Product> getProducts() {
        try {
            LOGGER.info("Finding products...");
            return jdbcTemplate.query(FIND_PRODUCTS, mapper);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving products ", e);
            return Collections.emptyList();
        }
    }
}
