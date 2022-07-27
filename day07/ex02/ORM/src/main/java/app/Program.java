/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 10:55:32 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 10:56:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import com.zaxxer.hikari.HikariDataSource;
import models.User;

public class Program {

    public static final String DB_URL = "jdbc:postgresql://localhost/";
    public static final String DB_USER = "postgres";

    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl(DB_URL);
            dataSource.setUsername(DB_USER);
            dataSource.setPassword(null);

            OrmManager ormManager = new OrmManager(dataSource);
            User user = new User("bob", "fisher", 100);
            ormManager.save(user);
            user.setId(1L);
            user.setFirstName("Tom");
            user.setLastName("Hardy");
            user.setAge(35);
            ormManager.update(user);
            User user1 = ormManager.findById(1L, User.class);
            System.out.println(user1);
        }
    }
}
