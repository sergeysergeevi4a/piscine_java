/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   StatServiceImpl.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:02:29 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:30 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.services;

import edu.school21.tanks.models.Stat;
import edu.school21.tanks.objects.Tanks;
import edu.school21.tanks.repositories.Statrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatServiceImpl implements StatService{
    @Autowired
    private Statrepository statrepository;
    @Override
    public void saveStat(Tanks first, Tanks second) {
        Stat stat = new Stat((int)first.getShots(), (int)first.getHits(), (int)(first.getShots() - first.getHits()),
                (int)second.getShots(), (int)second.getHits(), (int)(second.getShots() - second.getHits()));
        statrepository.save(stat);
    }

    @Override
    public String send2(Tanks enemy) {
        return "ENEMY: Shots: " + enemy.getShots() + " Hits:  "+ enemy.getHits() + " Mises: " + (enemy.getShots() - enemy.getHits());
    }

    @Override
    public String send1(Tanks me) {
        return "YOU: Shots: " + me.getShots() + " Hits: " + me.getHits() + " Mises: " + (me.getShots() - me.getHits());
    }
}
