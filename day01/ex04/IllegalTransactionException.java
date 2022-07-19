/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   IllegalTransactionException.java                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:52:44 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:53:21 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class IllegalTransactionException extends RuntimeException {
    
    public IllegalTransactionException(String s) {
        super(s);
    }
}
