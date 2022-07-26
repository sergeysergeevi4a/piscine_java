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

    public static final String CONNECTION_ERROR = "Error: can't connection to DB";
    public static final String SQL_QUERY_ERROR = "Error: SQLException";
    public static final String NOT_SAVED = "User or chatroom not saved";

    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String ROOM = "room";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";

    public static final String SELECT_ID = "SELECT * FROM messages WHERE id=";
    public static final String SELECT_FROM_USERS = "SELECT * FROM users WHERE id=";
    public static final String SELECT_FROM_ROOMS = "SELECT * FROM chatrooms WHERE id=";
    public static final String INSERT_INTO_MESSAGES = "INSERT INTO messages (author, room, text, time) VALUES (?, ?, ?, ?)";
    public static final String SELECT_FROM_MESSAGES = "SELECT * FROM messages";

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
            set = connection.createStatement().executeQuery(SELECT_ID + id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(SQL_QUERY_ERROR);
            return Optional.empty();
        }

        try {
            set.next();
            Long messageId = set.getLong(ID);
            long authorId = set.getLong(AUTHOR);
            long roomId = set.getLong(ROOM);
            String message = set.getString(TEXT);
            Timestamp timestamp = set.getTimestamp(TIME);

            ResultSet authorSet = connection
                    .createStatement()
                    .executeQuery(SELECT_FROM_USERS + authorId);
            ResultSet roomSet = connection
                    .createStatement()
                    .executeQuery(SELECT_FROM_ROOMS + roomId);

            authorSet.next();
            roomSet.next();

            Long authorIdSet = authorSet.getLong(ID);
            String authorLoginSet = authorSet.getString(LOGIN);
            String authorPasswordSet = authorSet.getString(PASSWORD);

            Long roomIdSet = roomSet.getLong(ID);
            String roomName = roomSet.getString(NAME);

            Message msg = new Message(
                    messageId,
                    new User(authorIdSet, authorLoginSet, authorPasswordSet, null, null),
                    new Chatroom(roomIdSet, roomName, null, null),
                    message, timestamp);

            return Optional.of(msg);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(SQL_QUERY_ERROR);
            System.exit(1);
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(INSERT_INTO_MESSAGES);
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new NotSavedSubEntityException(NOT_SAVED);
        }

        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery(SELECT_FROM_MESSAGES);
            while (set.next()) {
                if (set.isLast()) {
                    break;
                }
            }
            message.setId(set.getLong(ID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(SQL_QUERY_ERROR);
            System.exit(1);
        }
    }

    @Override

    public void update(Message message) {

        String query = "UPDATE messages SET " +
                "author = ?, " +
                "room = ?, " +
                "text = ?, " +
                "time = ? " +
                "WHERE id = ?;";
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.setLong(5, message.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new NotSavedSubEntityException("Error update");

        }

    }

    private void initConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(CONNECTION_ERROR);
            System.exit(1);
        }
    }
}
