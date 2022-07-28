/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PrinterWithDateTimeImpl.java                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:50:30 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:50:31 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package printer;

import renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String s) {
        renderer.render(s + " " + LocalDateTime.now());
    }
}
