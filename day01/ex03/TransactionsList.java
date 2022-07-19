/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsList.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:47:46 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:47:48 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

interface TransactionsList {

    void addTransaction(Transaction transaction);
    void removeTransactionById(UUID uuid);
    Transaction[] toArray();
    Integer getSize();
}
