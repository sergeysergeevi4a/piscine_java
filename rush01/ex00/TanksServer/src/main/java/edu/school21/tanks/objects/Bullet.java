/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Bullet.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:01:53 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:01:54 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.objects;

public class Bullet {
    private final Tanks sender;
    private Tanks enemy;
    private final float x;
    private float y;
    private final float step;

    public Bullet(Tanks sender, Tanks enemy){
        this.sender = sender;
        this.enemy = enemy;
        this.x = sender.getX();
        this.y = sender.getY();
        if (sender.getId() == 1)
            this.step = 2f;
        else
            this.step = -2f;
    }

    public boolean fly(){
        if (Math.abs(enemy.getX() - x) < 43f && Math.abs(enemy.getY() - y) < 32f)
            return true;
        this.y += this.step;
        return Math.abs(enemy.getX() - x) < 43f && Math.abs(enemy.getY() - y) < 32f;
    }

    public Tanks getSender() {
        return sender;
    }

    public Tanks getEnemy() {
        return enemy;
    }

    public void setEnemy(Tanks enemy) {
        this.enemy = enemy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
