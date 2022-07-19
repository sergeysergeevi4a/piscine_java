/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Transaction.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 13:25:06 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 16:02:26 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

public class Transaction {
	
	private final UUID identifier;
	
	private User recipient;
	
	private User sender;
	
	private Category category;
	
	private Integer amount;

	enum Category {
		DEBIT, CREDIT
	}
	
	public Transaction(User recipient, User sender, Category category, Integer amount) {
		this.identifier = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		this.category = category;
		setAmount(amount);
	}

	public void setAmount(Integer amount) {
		if (category == Category.CREDIT) {
			if (amount > 0) {
				this.amount = 0;
			} else {
				this.amount = amount;
			}
		} else if (category == Category.DEBIT) {
			if (amount < 0) {
				this.amount = 0;
			} else {
				this.amount = amount;
			}
		}
	}
}
