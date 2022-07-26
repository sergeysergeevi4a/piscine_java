/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:08:39 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:08:41 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost/");
            dataSource.setUsername("postgres");
            dataSource.setPassword(null);

            Connection connection;
            try {
                connection = dataSource.getConnection();
                if (connection == null) {
                    System.err.println("Error: can't connection to DB");
                    return;
                }
            } catch (SQLException e) {
                System.err.println("Error: can't connection to DB");
                return;
            }

            List<String> schemaQueries;
            List<String> dataQueries;

            try {
                schemaQueries = Files.readAllLines(Paths.get("../src/main/resources/schema.sql"));
                dataQueries = Files.readAllLines(Paths.get("../src/main/resources/data.sql"));
            } catch (IOException e) {
                System.err.println("Error: file not found");
                return;
            }

            createSchema(connection, schemaQueries);

            insertToDB(connection, dataQueries);

            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

            System.out.println("Enter a message ID");

            long l;

            try (Scanner scanner = new Scanner(System.in)) {
                l = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.err.println("Error: put long!");
                return;
            }

            Optional<Message> message = messagesRepository.findById(l);

            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.out.println("null");
            }
        }
    }

    public static void createSchema(Connection connection, List<String> schemaQueries) {
        for (String schemaQuery : schemaQueries) {
            try {
                connection.createStatement().execute(schemaQuery);
            } catch (SQLException e) {
                System.err.println("Error: SQLException");
                return;
            }
        }
    }

    public static void insertToDB(Connection connection, List<String> dataQueries) {
        for (String dataQuery : dataQueries) {
            try {
                connection.createStatement().execute(dataQuery);
            } catch (SQLException e) {
                System.err.println("Error: SQLException");
                return;
            }
        }
    }
}
