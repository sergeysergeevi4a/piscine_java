/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   BulletThread.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:02:05 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:06 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.server;

import edu.school21.tanks.objects.Bullet;

import java.util.LinkedList;

public class BulletThread extends Thread{
    private final Bullet bullet;

    public BulletThread(Bullet bullet, LinkedList<Bullet> bulletList){
        this.bullet = bullet;
    }

    @Override
    public void run() {
        while(bullet.getX() < 1000f && bullet.getX() > 10f && bullet.getY() > 10f && bullet.getY() < 1000f){
            if (bullet.fly()) {
                bullet.getSender().hitEnemy();
                bullet.getEnemy().hitMe();
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
