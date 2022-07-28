/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PreProcessorToUpperImpl.java                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:50:24 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:50:25 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package preprosessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String preProcess(String s) {
        return s.toUpperCase();
    }
}
