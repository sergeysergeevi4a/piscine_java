/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   StatrepositoryImpl.java                            :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:02:02 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:03 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.repositories;

import edu.school21.tanks.models.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StatrepositoryImpl implements Statrepository{

    public JdbcTemplate jdbcTemplate;
    @Autowired
    public StatrepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Stat entity) {
        jdbcTemplate.update("INSERT INTO stat (tank1, shots1, hits1, mises1, tank2, shots2, hits2, mises2) values (?, ?, ?, ?,?, ?, ?, ?)", "First tank",
                entity.getFirstShots(), entity.getFirstHits(), entity.getFirstMiss(), "Second tank",
                entity.getSecondShots(), entity.getSecondHits(), entity.getSecondMiss());
    }
}
