/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   SizeInterval.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 12:30:43 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 12:30:44 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game.parser;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class SizeInterval implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n < 5 || n > 100) {
            throw new ParameterException("Parameter " + name + " should be in interval {5, 100} (found " + value +")");
        }
    }
}