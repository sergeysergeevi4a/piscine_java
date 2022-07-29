/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 09:45:21 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 09:45:22 by kferterb         ###   ########.fr       */
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
