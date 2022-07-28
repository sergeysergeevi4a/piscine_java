/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersRepositoryJdbcImpl.java                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:57:17 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:57:18 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;
    private Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        init();
    }

    private void init() {
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        try {
            ResultSet set = connection.createStatement()
                    .executeQuery("SELECT FROM userTable " + "WHERE identifier=" + id);

            if (set.next()) {
                User user = new User();

                user.setIdentifier(set.getLong("identifier"));
                user.setEmail(set.getString("email"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        try {
            ResultSet set = connection.createStatement()
                    .executeQuery("SELECT * FROM userTable");

            while (set.next()) {
                User user = new User();
                user.setIdentifier(set.getLong("identifier"));
                user.setEmail(set.getString("email"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO userTable (email) VALUES (?)");
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            connection.createStatement()
                    .executeUpdate("UPDATE userTable SET email="
                            + entity.getEmail() + " " + "WHERE identifier="
                            + entity.getIdentifier());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            connection.createStatement()
                    .executeUpdate("DELETE FROM userTable " + "WHERE identifier=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            ResultSet set = connection.createStatement()
                    .executeQuery("SELECT FROM userTable WHERE email=" + email);

            if (set.next()) {
                User user = new User();

                user.setIdentifier(set.getLong("identifier"));
                user.setEmail(set.getString("email"));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
