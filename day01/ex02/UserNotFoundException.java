/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UserNotFoundException.java                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:28:49 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:28:50 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super(s);
    }
}
