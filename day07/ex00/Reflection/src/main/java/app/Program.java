/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 10:54:54 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 10:56:22 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static app.CallMethod.callMethod;
import static app.PrintFields.printFields;
import static app.PrintMethods.printMethods;
import static app.UpdateObject.createInstance;
import static app.UpdateObject.updateObject;

public class Program {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("classes", new SubTypesScanner(false));
        Set<Class<?>> set = reflections.getSubTypesOf(Object.class);

        List<String> classes = set.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toList());

        System.out.println("Classes:");

        for (String aClass : classes) {
            System.out.println(aClass);
        }

        System.out.println("Enter name of the field for changing:");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter class name:");

            String result = scanner.nextLine();

            if (!classes.contains(result)) {
                System.err.println("Error: there is no such class!");
                return;
            }

            Class<?> clazz = Class.forName("classes" + "." + result);
            System.out.println("---------------------");
            ArrayList<String> strings = printFields(clazz);
            printMethods(clazz);
            System.out.println("---------------------");
            Object object = createInstance(strings, clazz, scanner);

            if (object == null) {
                return;
            }

            System.out.println("---------------------");
            updateObject(object, scanner, strings);
            System.out.println("---------------------");
            callMethod(object, scanner);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
