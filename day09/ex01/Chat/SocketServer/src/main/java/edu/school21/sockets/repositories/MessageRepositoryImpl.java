/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessageRepositoryImpl.java                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 10:29:00 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 10:29:01 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessageRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return jdbcTemplate.query(
                        "SELECT FROM messageTable WHERE id=?",
                new Object[]{id},
                new int[]{},
                new BeanPropertyRowMapper<>(Message.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM messageTable",
                new BeanPropertyRowMapper<>(Message.class));
    }

    @Override
    public void save(Message entity) {
        jdbcTemplate.update("INSERT INTO messageTable (text, time) VALUES (?, ?)",
                entity.getText(),
                entity.getTime());
    }

    @Override
    public void update(Message entity) {
        jdbcTemplate.update("UPDATE messageTable SET text=?, time=? WHERE id=?",
                entity.getText(),
                entity.getTime(),
                entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM messageTable WHERE id=?", id);
    }
}
