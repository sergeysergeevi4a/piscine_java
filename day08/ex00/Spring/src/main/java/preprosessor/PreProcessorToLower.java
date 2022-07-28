/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PreProcessorToLower.java                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:50:21 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:50:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package preprosessor;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String preProcess(String s) {
        return s.toLowerCase();
    }
}
