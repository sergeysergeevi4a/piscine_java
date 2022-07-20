/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/19 17:09:46 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/20 10:54:25 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class Program {
	
	public static void main(String[] args) {
		User user1 = new User("John", -10000);

        User user2 = new User("John", 10000);
		
        User user3 = new User("Mike", 10000);


        
		System.out.println(user1.getIdentifier());
        System.out.println(user2.getIdentifier());
        System.out.println(user3.getIdentifier());

        System.out.println(UserIdsGenerator.getInstance().getId());
	}
}