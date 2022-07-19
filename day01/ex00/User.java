/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 13:25:03 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 15:38:06 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class User {
	
	private Integer identifier;

	private String	name;
	
	private Integer balance;

	public User(Integer identifier, Integer balance, String name) {
		this.identifier = identifier;
		
		this.name = name;

		if (balance > 0) {
			this.balance = balance;
		} else {
			this.balance = 0;
		}

		public Integer getIndentifier() {
			return identifier;
		}

		public void setIndefier(Integer identifier) {
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
}
