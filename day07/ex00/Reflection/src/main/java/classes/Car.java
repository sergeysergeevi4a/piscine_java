/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Car.java                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 10:55:00 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 10:56:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package classes;

public class Car {

    private final String model;
    private final String type;
    private final int fuelVolume;
    private final int consumption;

    public Car() {
        this.model = "default model";
        this.type = "default type";
        this.fuelVolume = 40;
        this.consumption = 8;
    }

    public Car(String model, String type, int fuelVolume, int consumption) {
        this.model = model;
        this.type = type;
        this.fuelVolume = fuelVolume;
        this.consumption = consumption;
    }

    public int distance() {
        return fuelVolume / consumption * 100;
    }

    public void ha(int i, int j) {
        System.out.println(i * j);
    }

    @Override
    public String toString() {
        return "Car[" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", fuelVolume=" + fuelVolume +
                ", consumption=" + consumption +
                ']';
    }
}
