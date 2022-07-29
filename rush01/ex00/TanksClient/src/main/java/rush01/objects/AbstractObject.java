/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   AbstractObject.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:00:04 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:00:05 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package rush01.objects;

import rush01.Constants;

public abstract class AbstractObject implements Constants {
	protected int x;
	protected int y;

	public AbstractObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
