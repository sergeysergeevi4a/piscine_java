/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 08:52:00 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 08:52:01 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(args);
        game.run();
    }
}
