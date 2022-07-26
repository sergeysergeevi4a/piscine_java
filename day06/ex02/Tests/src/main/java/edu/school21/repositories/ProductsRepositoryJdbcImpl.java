/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ProductsRepositoryJdbcImpl.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:46:48 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:46:49 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";

    public static final String SCHEMA = "schema.sql";
    public static final String DATA = "data.sql";

    private final Connection connection;

    public ProductsRepositoryJdbcImpl() throws SQLException {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .addScript(SCHEMA)
                .addScript(DATA)
                .build();
            connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();

        ResultSet set = connection
                .createStatement()
                .executeQuery("SELECT * FROM productTable");

        while (set.next()) {
            Product product = new Product(
                    set.getLong(ID),
                    set.getString(NAME),
                    set.getInt(PRICE));
            list.add(product);
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("SELECT * FROM productTable WHERE id=" + id);

        set.next();

        Product product = new Product(
                set.getLong(ID),
                set.getString(NAME),
                set.getInt(PRICE));

        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection
                .prepareStatement("UPDATE productTable SET name=? WHERE id=?");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setLong(2, product.getId());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("UPDATE productTable SET price=? WHERE id=?");
        preparedStatement.setInt(1, product.getPrice());
        preparedStatement.setLong(2, product.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void save(Product product) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO PRODUCTTABLE (NAME, PRICE) VALUES (?, ?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.execute();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement;

        preparedStatement = connection.prepareStatement("DELETE FROM PRODUCTTABLE WHERE ID=?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

    }
}
