package org.sample.store.repository.product;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Product
                .Builder()
                .withId(resultSet.getLong(1))
                .withProductName(resultSet.getString(2))
                .withPacksPerCarton(resultSet.getInt(3))
                .withCartonPrice(resultSet.getFloat(4))
                .build();
    }
}
