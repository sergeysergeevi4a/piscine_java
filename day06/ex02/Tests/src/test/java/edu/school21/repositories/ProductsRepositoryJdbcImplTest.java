/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ProductsRepositoryJdbcImplTest.java                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:47:03 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:47:04 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {

    private final ProductsRepositoryJdbcImpl productsRepositoryJdbc = new ProductsRepositoryJdbcImpl();

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ProductsRepositoryJdbcImpl().findAll();
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new ProductsRepositoryJdbcImpl().findById(1L).get();
    final Product EXPECTED_UPDATED_PRODUCT = productsRepositoryJdbc.findById(2L).get();

    public ProductsRepositoryJdbcImplTest() throws SQLException {
    }

    @Test
    public void findAllTest() {
        assertEquals(5, EXPECTED_FIND_ALL_PRODUCTS.size());
    }

    @Test
    public void findByIdTest() {
        assertEquals(1, EXPECTED_FIND_BY_ID_PRODUCT.getId());
    }

    @Test
    public void updateTest() throws SQLException {
        Product product = productsRepositoryJdbc.findById(2L).get();
        product.setName("superCar");
        product.setPrice(5000000);
        productsRepositoryJdbc.update(product);
        assertNotEquals(product.getName(), EXPECTED_UPDATED_PRODUCT.getName());
        assertNotEquals(product.getPrice(), EXPECTED_UPDATED_PRODUCT.getPrice());
    }

    @Test
    public void saveTest() throws SQLException {
        ProductsRepositoryJdbcImpl productsRepositoryJdbc = new ProductsRepositoryJdbcImpl();
        List<Product> products = productsRepositoryJdbc.findAll();
        productsRepositoryJdbc.save(new Product(null, "newProduct", 1000));
        assertNotEquals(products.size(), productsRepositoryJdbc.findAll().size());
    }

    @Test
    public void deleteTest() throws SQLException {
        ProductsRepositoryJdbcImpl productsRepositoryJdbc = new ProductsRepositoryJdbcImpl();
        List<Product> products = productsRepositoryJdbc.findAll();
        productsRepositoryJdbc.delete(0L);
        assertNotEquals(products.size(), productsRepositoryJdbc.findAll().size());
    }
}
