/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   StatService.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:02:26 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:27 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.services;

import edu.school21.tanks.objects.Tanks;
import org.springframework.stereotype.Component;

@Component
public interface StatService {
    void saveStat(Tanks first, Tanks second);
    String send1(Tanks me);
    String send2(Tanks enemy);
}
