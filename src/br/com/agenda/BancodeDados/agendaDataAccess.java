package br.com.agenda.BancodeDados;

import br.com.agenda.Modelo.agendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class agendaDataAccess {

    private agendaConexao conn; // sua classe de conexão
    private PreparedStatement preparestate;
    private ResultSet rset;

    public agendaDataAccess() {
        conn = new agendaConexao();
    }

    // Inserir
    public void inserir(agendaModel at) {
        String query = "INSERT INTO usuario (nome,endereco,email,cel_aux,celular,cpf,sobre) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = conn.getConnection();
             PreparedStatement preparestate = connection.prepareStatement(query)) {

            preparestate.setString(1, at.getNome());
            preparestate.setString(2, at.getEnd());
            preparestate.setString(3, at.getEmail());
            preparestate.setString(4, at.getCel_Aux());
            preparestate.setString(5, at.getCel());
            preparestate.setString(6, at.getCpf());
            preparestate.setString(7, at.getSobre());

            preparestate.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(agendaDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Atualizar
    public void atualizar(agendaModel at) {
        String query = "UPDATE usuario SET nome=?, endereco=?, email=?, cel_aux=?, celular=?, cpf=?, sobre=? WHERE id=?";
        try (Connection connection = conn.getConnection();
             PreparedStatement preparestate = connection.prepareStatement(query)) {

            preparestate.setString(1, at.getNome());
            preparestate.setString(2, at.getEnd());
            preparestate.setString(3, at.getEmail());
            preparestate.setString(4, at.getCel_Aux());
            preparestate.setString(5, at.getCel());
            preparestate.setString(6, at.getCpf());
            preparestate.setString(7, at.getSobre());
            preparestate.setInt(8, at.getId());

            preparestate.executeUpdate();
            JOptionPane.showMessageDialog(null, "Contato Atualizado!!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    // Deletar
    public void deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try (Connection connection = conn.getConnection();
             PreparedStatement preparestate = connection.prepareStatement(sql)) {

            preparestate.setInt(1, id);
            if (preparestate.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Contato deletado com sucesso!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

public void ApagarAgenda() {
    Connection connection = null;
    Statement stmt = null;

    // SQL para MySQL
    String sqlMySQL = "CREATE TABLE IF NOT EXISTS usuario("
            + "id int not null auto_increment,"
            + "nome varchar(50) not null,"
            + "endereco varchar(200) not null,"
            + "email varchar(100) not null,"
            + "cel_aux varchar(30),"
            + "celular varchar(30) not null,"
            + "cpf varchar(20) not null,"
            + "sobre varchar(255),"
            + "constraint usuario_id_pk primary key(id),"
            + "constraint usuario_cpf_uk unique (cpf));";

    // SQL para Postgres
    String sqlPostgres = "CREATE TABLE IF NOT EXISTS usuario("
            + "id serial not null,"
            + "nome varchar(100) not null,"
            + "endereco varchar(100) not null,"
            + "email varchar(100),"
            + "cpf varchar(20) not null,"
            + "celular varchar(30) not null,"
            + "cel_aux varchar(30) not null,"
            + "sobre varchar(255),"
            + "constraint id_usuario_pk primary key (id),"
            + "constraint cpf_usuario_uk unique (cpf));";

    try {
        connection = conn.getConnection(); // tenta MySQL, se falhar cai pro Postgres
        stmt = connection.createStatement();

        int r = JOptionPane.showConfirmDialog(null,
                "Essa ação apagará toda a Agenda. Quer continuar?",
                "ATENÇÃO!!!", JOptionPane.YES_NO_CANCEL_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            // Apaga tabela
            stmt.executeUpdate("DROP TABLE IF EXISTS usuario;");

            // Descobre qual driver está em uso
            String driverName = connection.getMetaData().getDriverName().toLowerCase();

            if (driverName.contains("mysql")) {
                stmt.executeUpdate(sqlMySQL);
            } else if (driverName.contains("postgresql")) {
                stmt.executeUpdate(sqlPostgres);
            }

            JOptionPane.showMessageDialog(null, "Agenda apagada e recriada!");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}

    // Listar
    public List<agendaModel> getContatos(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        List<agendaModel> lista = new ArrayList<>();

        try (Connection connection = conn.getConnection();
             PreparedStatement preparestate = connection.prepareStatement(sql)) {

            preparestate.setString(1, "%" + nome + "%");
            try (ResultSet rset = preparestate.executeQuery()) {
                while (rset.next()) {
                    agendaModel atributos = new agendaModel();
                    atributos.setId(rset.getInt("id"));
                    atributos.setNome(rset.getString("nome"));
                    atributos.setEnd(rset.getString("endereco"));
                    atributos.setEmail(rset.getString("email"));
                    atributos.setCel_Aux(rset.getString("cel_aux"));
                    atributos.setCel(rset.getString("celular"));
                    atributos.setCpf(rset.getString("cpf"));
                    atributos.setSobre(rset.getString("sobre"));
                    lista.add(atributos);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista;
    }
}
