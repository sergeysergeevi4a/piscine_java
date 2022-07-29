/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   CrudRepository.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 10:28:52 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 10:28:53 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.repositories;

import java.util.List;

public interface CrudRepository<T> {

    T findById(Long id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}
