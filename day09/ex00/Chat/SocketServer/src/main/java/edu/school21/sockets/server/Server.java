/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Server.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 09:49:00 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 09:49:01 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final String[] args;
    private int port;

    public Server(String[] args) {
        this.args = args;
    }

    public void start() {
        if (checkArgs()) {
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                out.write("Hello from Server!\n");
                out.flush();

                String answer = in.readLine();
                if (!answer.equals("signUp")) {
                    out.write("Error: you should to put \"signUp\"\n");
                    out.flush();
                    return;
                }

                out.write("Enter username:\n");
                out.flush();

                String userName = in.readLine();

                out.write("Enter password:\n");
                out.flush();

                String password = in.readLine();

                ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

                UsersService usersService = context.getBean(UsersService.class);

                out.write(usersService.saveNewUser(userName, password));
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkArgs() {
        if (args.length != 1) {
            System.err.println("Error: bad args");
            return true;
        }

        if (!args[0].startsWith("--port=")) {
            System.err.println("Error: bad params");
            return true;
        }

        try {
            port = Integer.parseInt(args[0].replaceFirst("--port=", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error: put number!");
            return true;
        }

        return false;
    }
}
