/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UserIdsGenerator.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:54:08 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:54:12 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class UserIdsGenerator {

    private Integer id = 0;

    private static UserIdsGenerator userIdsGenerator;

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

    public int generateId() {
        return ++id;
    }

    public Integer getId() {
        return id;
    }
}
