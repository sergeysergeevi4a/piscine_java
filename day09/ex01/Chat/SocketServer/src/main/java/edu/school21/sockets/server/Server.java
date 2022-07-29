/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Server.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 10:29:13 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 10:29:14 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private final String[] args;
    private int port;
    public static final ArrayList<MultiServer> servers = new ArrayList<>();

    public Server(String[] args) {
        this.args = args;
    }

    public void start() {
        if (checkArgs()) {
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started");

            while (true) {
                Socket socket = serverSocket.accept();
                new MultiServer(socket);
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
