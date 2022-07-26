/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessagesRepositoryJdbcImpl.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:07:24 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:07:25 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Connection connection;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Error: can't connection to DB");
            return Optional.empty();
        }

        ResultSet set;

        try {
            set = connection.createStatement()
                    .executeQuery("SELECT * FROM messages WHERE id=" + id);
        } catch (SQLException e) {
            System.err.println("Error: SQLException");
            return Optional.empty();
        }


        try {
            set.next();
            Long messageId = set.getLong("id");
            long authorId = set.getLong("author");
            long roomId = set.getLong("room");
            String message = set.getString("text");
            Timestamp timestamp = set.getTimestamp("time");

            ResultSet authorSet = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users WHERE id=" + authorId);
            ResultSet roomSet = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM chatrooms WHERE id=" + roomId);

            authorSet.next();
            roomSet.next();
            Long authorIdSet = authorSet.getLong("id");
            String authorLoginSet = authorSet.getString("login");
            String authorPasswordSet = authorSet.getString("password");
            Long roomIdSet = roomSet.getLong("id");
            String roomName = roomSet.getString("name");

            Message msg = new Message(
                    messageId,
                    new User(authorIdSet, authorLoginSet, authorPasswordSet, null, null),
                    new Chatroom(roomIdSet, roomName, null, null),
                    message, timestamp);
            return Optional.of(msg);
        } catch (SQLException e) {
            System.err.println("Error: SQLException");
            System.exit(1);
        }
        return Optional.empty();
    }
}
