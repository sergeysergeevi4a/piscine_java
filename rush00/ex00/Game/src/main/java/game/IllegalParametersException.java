/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   IllegalParametersException.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 08:51:57 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 08:51:58 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException(String msg) {
        super(msg);
    }
}
