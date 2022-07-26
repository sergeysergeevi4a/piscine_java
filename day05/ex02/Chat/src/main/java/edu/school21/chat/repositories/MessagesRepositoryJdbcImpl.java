/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessagesRepositoryJdbcImpl.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:44:44 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:44:45 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    private Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        initConnection();
    }

    @Override
    public Optional<Message> findById(Long id) {
        ResultSet set;

        try {
            set = connection.createStatement().executeQuery("SELECT * FROM messages WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            System.err.println("Error: SQLException");
            System.exit(1);
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO messages (author, room, text, time) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new NotSavedSubEntityException("User or chatroom not saved");
        }

        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM messages");
            while (set.next()) {
                if (set.isLast()) {
                    break;
                }
            }
            message.setId(set.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: SQLException");
            System.exit(1);
        }
    }

    private void initConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Error: can't connection to DB");
            System.exit(1);
        }
    }
}
