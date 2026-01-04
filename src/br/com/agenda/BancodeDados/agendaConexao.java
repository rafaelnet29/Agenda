package br.com.agenda.BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class agendaConexao {

    private Connection conn;
    /**
     * Conexão com o MySQL
     */
    private final String urlMySQL = "jdbc:mysql://localhost:3306/cad";
    private final String userMySQL = "root";
    private final String passMySQL = "qwerty";
    private static final String DriverclassMySQL = "com.mysql.cj.jdbc.Driver";

    /**
     * Conexão com o Postgres
     */
    private final String urlPostgres = "jdbc:postgresql://localhost:5432/cad";
    private final String userPostgres = "postgres";
    private final String passPostgres = "postgres";
    private static final String DriverclassPostgres = "org.postgresql.Driver";

    // Método construtor
    public agendaConexao() {
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DriverclassMySQL);
            conn = DriverManager.getConnection(urlMySQL, userMySQL, passMySQL);
            System.out.println("Conectado ao MySQL!");
        } catch (ClassNotFoundException | SQLException exMySQL) {
            System.out.println("Falha no MySQL, tentando Postgres...");
            try {
                Class.forName(DriverclassPostgres);
                conn = DriverManager.getConnection(urlPostgres, userPostgres, passPostgres);
                System.out.println("Conectado ao Postgres!");
            } catch (ClassNotFoundException | SQLException exPostgres) {
                System.out.println("Falha no Postgres");
            }
        }
        return conn;
    }
}
