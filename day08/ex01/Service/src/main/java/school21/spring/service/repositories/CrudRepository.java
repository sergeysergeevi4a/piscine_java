/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   CrudRepository.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:57:12 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:57:13 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package school21.spring.service.repositories;

import java.util.List;

public interface CrudRepository<T> {

    T findById(Long id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}
