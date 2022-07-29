/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 09:42:46 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 09:42:48 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import client.Client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client(args);
        client.start();
    }
}
