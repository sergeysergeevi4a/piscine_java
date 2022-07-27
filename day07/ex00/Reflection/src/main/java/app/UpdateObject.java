/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UpdateObject.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/27 11:21:06 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/27 11:21:07 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Scanner;

import static app.GetParamObject.getParamObject;

public class UpdateObject {

    static void updateObject(Object object, Scanner scanner, ArrayList<String> strings) {
        System.out.println("Enter name of the field for changing:");
        String field = scanner.nextLine();

        if (!strings.contains(field)) {
            System.err.println("Error: can't find field");
            System.exit(1);
        }

        System.out.println("Enter String value:");
        String value = scanner.nextLine();

        try {
            Field fieldForUpdate = object.getClass().getDeclaredField(field);
            fieldForUpdate.setAccessible(true);

            try {
                switch (fieldForUpdate.getType().getSimpleName().toLowerCase()) {
                    case "int":
                    case "integer":
                        fieldForUpdate.set(object, Integer.parseInt(value));
                        break;
                    case "double":
                        fieldForUpdate.set(object, Double.parseDouble(value));
                        break;
                    case "boolean":
                        fieldForUpdate.set(object, Boolean.parseBoolean(value));
                        break;
                    case "long":
                        fieldForUpdate.set(object, Long.parseLong(value));
                        break;
                    default:
                        fieldForUpdate.set(object, value);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: can't find such method");
                System.exit(1);
            }

            fieldForUpdate.setAccessible(false);
            System.out.println("Object updated: " + object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Object createInstance(ArrayList<String> strings, Class<?> clazz, Scanner scanner) {
        System.out.println("Let's create an object.");
        Constructor<?> constructor = null;

        for (Constructor<?> c : clazz.getConstructors()) {
            if (c.getParameterTypes().length > 0) {
                constructor = c;
                break;
            }
        }

        ArrayList<Object> params = new ArrayList<>();

        if (constructor != null) {
            Parameter[] parameters = constructor.getParameters();

            for (int i = 0; i < parameters.length; i++) {
                System.out.println(strings.get(i) + ":");
                params.add(getParamObject(parameters[i], scanner));
            }

            if (!(params.get(params.size() - 1) instanceof String)) {
                scanner.nextLine();
            }

            try {
                Object object = constructor.newInstance(params.toArray());
                System.out.println("Object created: " + object);
                return object;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
