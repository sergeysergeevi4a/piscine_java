/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessagesRepository.java                            :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/25 20:44:41 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/25 20:44:42 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);

    void save(Message message);
    void update(Message message);
}
