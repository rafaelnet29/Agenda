package br.com.agenda.BancodeDados;

import br.com.agenda.Modelo.agendaModel;
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

    private final agendaConexao conn;
    private PreparedStatement preparestate = null;
    private final agendaModel atributos;

    //Método Construtor
    public agendaDataAccess() {
        this.preparestate = preparestate;
        this.conn = new agendaConexao();
        this.atributos = new agendaModel();
    }

    //Método para inserir contatos via SQL
    public void inserir(agendaModel at) {
        String query = "INSERT INTO usuario (nome,endereco,email,celular_aux,celular,cpf,sobre) VALUES(?,?,?,?,?,?,?)";

        try {
            preparestate = conn.getConectMySQl().prepareStatement(query);

            preparestate.setString(1, atributos.getNome());
            preparestate.setString(2, atributos.getEnd());
            preparestate.setString(3, atributos.getEmail());
            preparestate.setString(4, atributos.getCel_Aux());
            preparestate.setString(5, atributos.getCel());
            preparestate.setString(6, atributos.getCpf());
            preparestate.setString(7, atributos.getSobre());

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
            preparestate.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(agendaDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (conn.getConectMySQl() != null && preparestate != null) {
                try {
                    conn.getConectMySQl().close();
                    preparestate.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    //Método para  atualizar contatos via SQL
    public void Atualizar(agendaModel at) {
        String query = " UPDATE usuario SET nome = ?, endereco = ?, email = ?, celular_aux = ?, celular = ?, cpf = ?, sobre = ?"
                + " WHERE id = ?";
        try {
            preparestate = conn.getConectMySQl().prepareStatement(query);
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
            JOptionPane.showMessageDialog(null, " Opá! Erro ao atualizar " + ex.getSQLState() + ex.getErrorCode());
        } finally {
            if (conn.getConectMySQl() != null && preparestate != null) {
                try {
                    conn.getConectMySQl().close();
                    preparestate.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    //Método para deletar contatos via SQL
    public void Deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            preparestate = conn.getConectMySQl().prepareStatement(sql);
            preparestate.setInt(1, id);
            if (preparestate.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Contato deletado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            if (conn.getConectMySQl() != null && preparestate != null) {
                try {
                    conn.getConectMySQl().close();
                    preparestate.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

    }

    //Método para recriar a tablela contatos via SQL
    public void ApagarAgenda() {
        try {
            Statement stmt = this.conn.getConectMySQl().createStatement();
            String sql = "drop table if exists usuario;";
            String sql2 = "create table if not exists usuario("
                    + "\n id int not null auto_increment,"
                    + "\n nome varchar(50) not null,"
                    + "\n endereco varchar(200) not null,"
                    + "\n email varchar(100)not null,"
                    + "\n celular_aux varchar(30),"
                    + "\n celular varchar(30) not null,"
                    + "\n cpf varchar(20)not null,"
                    + "\n sobre varchar(255),"
                    + "\n constraint usuario_id_pk primary key(id),"
                    + "\n constraint usuario_cpf_uk unique (cpf)\n);";
            int r = JOptionPane.showConfirmDialog(null,
                    " Essa ação apagará toda a Agenda. Quer continuar ? ", " ATENÇÂO!!! ", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == 0) {
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.close();
                JOptionPane.showMessageDialog(null, " Agenda apagada! ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            if (conn.getConectMySQl() != null && preparestate != null) {
                try {
                    conn.getConectMySQl().close();
                    preparestate.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    //Método para listar contatos via SQL
    public List<agendaModel> getContatos(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome LIKE '%" + nome + "%'";
        List<agendaModel> lista = new ArrayList<>();
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        try {
            preparestate = conn.getConectMySQl().prepareStatement(sql);
            rset = preparestate.executeQuery();
            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                //Recupera o id do banco e atribui ele ao objeto
                atributos.setId(rset.getInt("id"));
                //Recupera o nome do banco e atribui ele ao objeto
                atributos.setNome(rset.getString("nome"));
                //Recupera a endereco do banco e atribui ele ao objeto
                atributos.setEnd(rset.getString("endereco"));
                //Recupera o email do banco e atribui ela ao objeto
                atributos.setEmail(rset.getString("email"));
                //Recupera o telefone auxiliar
                atributos.setCel_Aux(rset.getString("celular_aux"));
                //Recupera o telefone celular
                atributos.setCel(rset.getString("celular"));
                //Recupera o cpf
                atributos.setCpf(rset.getString("cpf"));
                //Recupera a descrição do usuario
                atributos.setSobre(rset.getString("sobre"));
                //Adiciono o contato recuperado, a lista de contatos
                lista.add(atributos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " " + JOptionPane.INFORMATION_MESSAGE);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (preparestate != null) {
                    preparestate.close();
                }
                if (conn != null) {
                    conn.getConectMySQl().close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }
}