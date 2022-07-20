/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 13:25:03 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/20 19:17:13 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class User {
	
	private Integer identifier;

	private String	name;
	
	private Integer balance;

	public User(Integer identifier, String name, Integer balance) {
		this.identifier = identifier;
		
		this.name = name;

		if (balance < 0) {
			this.balance = 0;
		} else {
			this.balance = balance;
		}
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
}