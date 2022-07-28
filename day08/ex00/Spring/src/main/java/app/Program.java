/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/28 12:50:15 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/28 12:50:16 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import printer.Printer;
//import preprosessor.PreProcessor;
//import preprosessor.PreProcessorToLower;
//import preprosessor.PreProcessorToUpperImpl;
//import printer.PrinterWithDateTimeImpl;
//import printer.PrinterWithPrefixImpl;
//import renderer.Renderer;
//import renderer.RendererErrImpl;
//import renderer.RendererStandardImpl;

public class Program {

//    public static void main(String[] args) {
//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer.setPrefix("Prefix ");
//        printer.print("Hello!");
//
//        PreProcessor preProcessor1 = new PreProcessorToLower();
//        Renderer renderer1 = new RendererStandardImpl(preProcessor1);
//        PrinterWithDateTimeImpl printer1 = new PrinterWithDateTimeImpl(renderer1);
//        printer1.print("Hello!");
//    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix1", Printer.class);
        printer.print("Hello!");
    }
}
