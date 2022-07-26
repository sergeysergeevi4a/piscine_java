/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   IllegalNumberException.java                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:46:39 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:46:40 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {

    public IllegalNumberException(String s) {
        super(s);
    }
}
