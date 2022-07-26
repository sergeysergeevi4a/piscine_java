/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:44:26 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:44:27 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.app;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {

    public static void main(String[] args) throws SQLException {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("kferterb");
        dataSource.setPassword("");
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> optionalMessage = repository.findById(5L);

        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            System.out.println("Original message");
            System.out.println(repository.findById(5L).get());
            message.setText("Bye");
            message.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
            repository.update(message);
            System.out.println("Updated message");
            System.out.println(repository.findById(5L).get());
        }
    }
}

