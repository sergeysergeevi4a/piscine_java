/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   PrintMethods.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 11:21:01 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 11:21:02 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import java.lang.reflect.Method;

public class PrintMethods {

    static void printMethods(Class<?> clazz) {
        System.out.println("methods:");

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (!method.getName().equals("toString")) {
                String returnType = method.getReturnType().getSimpleName();

                String name = method.getName();

                Class<?>[] parameters = method.getParameterTypes();

                if (parameters.length > 0) {
                    StringBuilder stringBuilder = new StringBuilder(parameters[0].getSimpleName());

                    for (int i = 1; i < parameters.length; i++) {
                        stringBuilder.append(", ");
                        stringBuilder.append(parameters[i].getSimpleName());
                    }

                    System.out.println("\t" + returnType + " " + name + "(" + stringBuilder + ")");
                } else {
                    System.out.println("\t" + returnType + " " + name + "()");
                }
            }
        }
    }
}
