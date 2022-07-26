/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   NotSavedSubEntityException.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:44:22 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:44:23 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.app;

public class NotSavedSubEntityException extends RuntimeException {

    public NotSavedSubEntityException(String s) {

        super(s);
    }
}
