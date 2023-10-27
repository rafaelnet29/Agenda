package br.com.agenda.BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agendaConexao {

    private Connection conn;
    /**
     * Conexão com o MySQL
    */
    private final String urlMySQL = "jdbc:mysql://localhost:3306/cad?useTimezone=true&serverTimezone=UTC";
    private final String userMySQL = "root";
    private final String passMySQL = "qwerty";
    private static final String DriverclassMySQL = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Conexão com o Postgres
    */
    private final String urlPostgres = "jdbc:postgres://localhost:5432/cad?useTimezone=true&serverTimezone=UTC";
    private final String userPostgres = "postgres";
    private final String passPostgres = "postgres";
    private static final String DriverclassPostgres = "org.postgresql.Driver";
    // Método construtor
    public agendaConexao(){
    }

    public Connection getConectMySQl() {

        try {
            Class.forName(DriverclassMySQL);
            conn = DriverManager.getConnection(urlMySQL, userMySQL, passMySQL);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agendaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
     public Connection getConectPostgres() {

        try {
            Class.forName(DriverclassPostgres);
            conn = DriverManager.getConnection(urlPostgres, userPostgres, passPostgres);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agendaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}