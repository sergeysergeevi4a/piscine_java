/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:06:11 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:29:41 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

public class Main {
    public static void main(String[] args) {
        InitGame initGame;
        try {
            initGame = new InitGame(args);
            initGame.run();
        } catch (IllegalParametersException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
