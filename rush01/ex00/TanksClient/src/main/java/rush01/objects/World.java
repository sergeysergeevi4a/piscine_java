/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   World.java                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:00:17 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:00:18 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.objects;

import java.util.List;

public class World {
    public List<Player> players;

    @Override
    public String toString() {
        return "World{" +
                "players=" + players +
                '}';
    }
}
