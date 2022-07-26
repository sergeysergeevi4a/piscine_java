/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   EmbeddedDataSourceTest.java                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/26 16:46:59 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/26 16:47:00 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmbeddedDataSourceTest {

    public static final String SCHEMA_SQL = "schema.sql";
    public static final String DATA_SQL = "data.sql";

    private DataSource dataSource;

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .addScript(SCHEMA_SQL)
                .addScript(DATA_SQL)
                .build();
    }

    @Test
    void connectionTest() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }
}
