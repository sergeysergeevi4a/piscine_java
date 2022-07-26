/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Product.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:46:36 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:46:37 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.models;

public class Product {

    private Long id;
    private String name;
    private Integer price;

    public Product(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
