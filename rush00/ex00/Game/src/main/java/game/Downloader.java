/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Downloader.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/24 08:51:51 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/24 08:51:52 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Downloader {

    public static final String PRODUCTION_PATH = "src/main/resources/application-production.properties";
    public static final String DEV_PATH = "src/main/resources/application-dev.properties";
    public static final String COMMON_PATH = "src/main/resources/";
    public static final String PROPERTY = ".properties";
    public static final String PRODUCTION_PROFILE = "production";
    public static final String DEV_PROFILE = "dev";

    private final String profile;

    public Downloader(String profile) {
        this.profile = profile;
    }

    public Properties download() throws IOException {
        Properties properties = new Properties();
        if (profile.equals(PRODUCTION_PROFILE)) {
            properties.load(new FileReader(PRODUCTION_PATH));
        } else if (profile.equals(DEV_PROFILE)) {
            properties.load(new FileReader(DEV_PATH));
        } else {
            properties.load(new FileReader(COMMON_PATH + profile + PROPERTY));
        }
        return properties;
    }
}
