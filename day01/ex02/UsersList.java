/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersList.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:28:57 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:28:58 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */


interface UsersList {

    void addUser(User user);
    User getUserById(Integer id);
    User getUserByIndex(Integer index);
    Integer getUsersCount();
}
