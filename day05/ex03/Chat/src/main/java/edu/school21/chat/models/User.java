/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:44:38 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:44:39 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private final Long id;
    private final String login;
    private final String password;
    private final List<Chatroom> rooms;
    private final List<Chatroom> socializedRooms;

    public User(Long id, String login, String password, List<Chatroom> rooms, List<Chatroom> socializedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rooms = rooms;
        this.socializedRooms = socializedRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(rooms, user.rooms) && Objects.equals(socializedRooms, user.socializedRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, rooms, socializedRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rooms=" + rooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }
}
