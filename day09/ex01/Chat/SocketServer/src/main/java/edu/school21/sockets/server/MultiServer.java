/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MultiServer.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 10:29:10 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 10:29:11 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.services.MessageService;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MultiServer extends Thread {

    private final BufferedReader in;
    private final BufferedWriter out;

    public MultiServer(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);
            MessageService messageService = context.getBean(MessageService.class);

            out.write("Hello from Server!\n");
            out.flush();

            String answer;

            while (true) {
                answer = in.readLine();

                if (!answer.equals("signUp") && !answer.equals("signIn")) {
                    out.write("Error: command not found\n");
                    out.flush();
                } else {
                    break;
                }
            }

            out.write("Enter username:\n");
            out.flush();

            String userName = in.readLine();

            out.write("Enter password:\n");
            out.flush();

            String password = in.readLine();

            if (answer.equals("signUp")) {
                out.write(usersService.saveNewUser(userName, password));
                out.flush();
            } else {
                if (!usersService.validateUser(userName, password)) {
                    out.write("Incorrect password!\n");
                    out.flush();
                    return;
                }

                Server.servers.add(this);

                out.write("Start messaging\n");
                out.flush();

                while (true) {
                    answer = in.readLine();

                    if (!answer.equals("exit")) {
                        messageService.saveMessage(new Message(answer, Timestamp.valueOf(LocalDateTime.now())));
                    }

                    if (answer.equals("exit")) {
                        out.write("You have left the chat.\n");
                        out.flush();
                        Server.servers.removeIf(server -> server == this);
                        break;
                    }

                    for (MultiServer server : Server.servers) {
                        server.sendMessage(userName + ": " + answer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String answer) {
        try {
            out.write(answer + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
