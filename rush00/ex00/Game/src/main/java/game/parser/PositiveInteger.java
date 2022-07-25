/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PositiveInteger.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 12:30:41 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 12:30:42 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game.parser;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class PositiveInteger implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n < 0) {
            throw new ParameterException("Parameter " + name + " should be positive (found " + value +")");
        }
    }
}
