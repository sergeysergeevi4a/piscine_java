/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:57:09 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:57:10 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package school21.spring.service.models;

public class User {

    private Long Identifier;
    private String Email;

    public Long getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Long identifier) {
        Identifier = identifier;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
