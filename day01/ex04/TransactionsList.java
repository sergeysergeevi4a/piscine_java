/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsList.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 20:53:47 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/19 20:53:49 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

interface TransactionsList {

    void addTransaction(Transaction transaction);
    void removeTransactionById(UUID uuid);
    Transaction[] toArray();
    Integer getSize();
}
