/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   EnemiesNotNull.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 12:30:34 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 12:30:36 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game.parser;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class EnemiesNotNull implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        int n = Integer.parseInt(value);
        if (n == 0) {
            throw new ParameterException("Parameter " + name + " cannot be null (found " + value +")");
        }
    }
}
