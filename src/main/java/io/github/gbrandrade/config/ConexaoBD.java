package io.github.gbrandrade.config;

import java.io.IOException;
import java.sql.Connection;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {

    public static Connection conectar() throws SQLException, IOException {

        Properties props = new Properties();
        InputStream input = ConexaoBD.class.getResourceAsStream("/database.properties");
        props.load(input);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        Connection conexao = DriverManager.getConnection(url, user, password);
        return conexao;

    }

}