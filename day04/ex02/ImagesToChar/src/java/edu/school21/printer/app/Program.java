/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/22 18:29:16 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/22 18:41:33 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.printer.app;

import java.io.IOException;
import java.net.URL;

import com.beust.jcommander.JCommander;

import edu.school21.printer.logic.Logic;


public class Program {
    public static void main(String[] args) {
        URL path = Program.class.getResource("/resources/image.bmp");
        Logic image;
        try {
            image = new Logic(path);
            JCommander.newBuilder()
                    .addObject(image)
                    .build()
                    .parse(args);
        } catch (Exception e) {
            System.out.println("Bad arguments");
            return;
        }

        try {
            image.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
