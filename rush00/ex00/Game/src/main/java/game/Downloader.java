/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Downloader.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 09:05:59 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 10:11:10 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Downloader {

    private final String profile;

    public Downloader(String profile) {
        this.profile = profile;
    }

    public Properties download() throws IOException {
        Properties properties = new Properties();

        InputStream inputStream;

        String propFileName = String.format("application-%s.properties", profile);

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);


        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        properties.load(inputStream);

        return properties;
    }
}
