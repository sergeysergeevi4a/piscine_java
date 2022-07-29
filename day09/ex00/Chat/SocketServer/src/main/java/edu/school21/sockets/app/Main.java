/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 09:45:51 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 09:45:52 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(args);
        server.start();
    }
}
