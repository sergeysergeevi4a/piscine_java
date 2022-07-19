/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionNotFoundException.java                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:53:36 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:53:37 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String s) {
        super(s);
    }
}
