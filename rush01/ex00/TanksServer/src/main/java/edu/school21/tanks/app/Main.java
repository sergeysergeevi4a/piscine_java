/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:43 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:01:44 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.app;


import edu.school21.tanks.config.TanksApplicationConfig;
import edu.school21.tanks.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        int port = 8081;
        ApplicationContext context = new AnnotationConfigApplicationContext(TanksApplicationConfig.class);
        Server server = context.getBean(Server.class);
        server.start(port);
    }
}
