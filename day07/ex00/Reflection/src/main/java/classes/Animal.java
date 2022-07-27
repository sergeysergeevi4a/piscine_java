/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Animal.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 10:54:57 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 10:56:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package classes;

public class Animal {

    private String name;
    private Integer numberOfLegs;
    private Double weight;
    private boolean alive;
    private Long id;

    public Integer greeting(String name){
        System.out.println(name);
        return (5454545);
    }

    public Animal() {
    }

    public Animal(String name, Integer numberOfLegs, Double weight, boolean alive, Long id) {
        this.name = name;
        this.numberOfLegs = numberOfLegs;
        this.weight = weight;
        this.alive = alive;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", numberOfLegs=" + numberOfLegs +
                ", weight=" + weight +
                ", alive=" + alive +
                ", id=" + id +
                '}';
    }
}