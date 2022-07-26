/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ProductsRepository.java                            :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:46:45 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:46:46 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

    List<Product> findAll() throws SQLException;
    Optional<Product> findById(Long id) throws SQLException;
    void update(Product product) throws SQLException;
    void save(Product product) throws SQLException;
    void delete (Long id) throws SQLException;
}
