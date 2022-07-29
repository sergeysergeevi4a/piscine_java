/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Client.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 09:42:50 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 09:44:40 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private final String[] args;
    private int port;

    public Client(String[] args) {
        this.args = args;
    }

    public void start() {
        if (checkArgs()) {
            return;
        }

        try (Socket socket = new Socket("localhost", port)) {
            try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println(in.readLine());

                String signUp = console.readLine();
                out.write(signUp + "\n");
                out.flush();

                String answer = in.readLine();
                System.out.println(answer);

                if (answer.equals("Error: you should to put \"signUp\"")) {
                    return;
                }

                String userName = console.readLine();

                out.write(userName + "\n");
                out.flush();

                System.out.println(in.readLine());

                String password = console.readLine();

                out.write(password + "\n");
                out.flush();

                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error: server is down!");
        }
    }

    private boolean checkArgs() {
        if (args.length != 1) {
            System.err.println("Error: bad args");
            return true;
        }

        if (!args[0].startsWith("--server-port=")) {
            System.err.println("Error: bad params");
            return true;
        }

        try {
            port = Integer.parseInt(args[0].replaceFirst("--server-port=", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error: put number!");
            return true;
        }

        return false;
    }
}
