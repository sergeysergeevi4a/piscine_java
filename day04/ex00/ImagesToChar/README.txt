# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    README.txt                                         :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2022/07/22 13:39:09 by kferterb          #+#    #+#              #
#    Updated: 2022/07/22 13:40:34 by kferterb         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

#0. Delete old directory:
rm -rf target

#1. Create directory:
mkdir target

#2. Compile files to the 'target' directory:
javac -d target src/java/edu/school21/printer/*/*.java

#3. Run program:
java -classpath target edu.school21.printer.app.Program . 0 src/java/edu/school21/printer/resources/it.bmp