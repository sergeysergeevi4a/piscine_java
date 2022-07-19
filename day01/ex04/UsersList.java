/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersList.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:54:32 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:54:35 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

interface UsersList {

    void addUser(User user);
    User getUserById(Integer id);
    User getUserByIndex(Integer index);
    Integer getUsersCount();
}
