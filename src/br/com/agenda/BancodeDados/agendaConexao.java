package br.com.agenda.BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agendaConexao {

    static Connection conn;
    /**
     * Conexão como MySQL
     * 
    */
    private final String url = "jdbc:mysql://localhost:3306/cad?useTimezone=true&serverTimezone=UTC";
    private final String user = "root";
    private final String pass = "";
    private static final String Driverclass = "com.mysql.cj.jdbc.Driver";
    
    // Método construtor
    public agendaConexao(){
    }

    public Connection getConectMySQl() {

        try {
            Class.forName(Driverclass);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agendaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}