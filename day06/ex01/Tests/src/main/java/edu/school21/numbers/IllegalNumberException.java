/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   IllegalNumberException.java                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 15:52:20 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 15:52:21 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {

    public IllegalNumberException(String s) {
        super(s);
    }
}
