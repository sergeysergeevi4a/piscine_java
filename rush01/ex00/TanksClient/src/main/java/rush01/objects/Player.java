/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Player.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:00:10 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:00:12 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.objects;

import java.util.List;

public class Player {
    public Tank tank;
    public List<Bullet> bullets;

    @Override
    public String toString() {
        return "Player{" +
                "tank=" + tank +
                ", bullets=" + bullets +
                '}';
    }
}
