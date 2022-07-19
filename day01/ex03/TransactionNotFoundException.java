/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionNotFoundException.java                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:47:38 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:47:40 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String s) {
        super(s);
    }
}
