/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PrinterWithPrefixImpl.java                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:50:32 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:50:33 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private Renderer renderer;
    private String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String s) {
        renderer.render(prefix + s);
    }
}
