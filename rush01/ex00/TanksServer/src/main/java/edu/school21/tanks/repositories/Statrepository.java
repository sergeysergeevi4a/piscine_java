/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Statrepository.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:59 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:00 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.repositories;

import edu.school21.tanks.models.Stat;

public interface Statrepository {
    void save(Stat entity);
}
