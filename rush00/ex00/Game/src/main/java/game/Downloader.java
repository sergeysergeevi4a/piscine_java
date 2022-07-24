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

import java.io.*;
import java.util.Properties;

public class Downloader {

    private final String profile;

    public Downloader(String profile) {
        this.profile = profile;
    }

    public Properties download() throws IOException {
        Properties properties = new Properties();

        String propFileName = String.format("application-%s.properties", profile);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException(propFileName);
            }
            properties.load(inputStream);
        }

        return properties;
    }
}
