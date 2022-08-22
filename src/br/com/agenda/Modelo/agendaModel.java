package br.com.agenda.Modelo;

public class agendaModel {
    private int id;
    private String nome;
    private String end;
    private String email;
    private String cel;
    private String cel_aux;
    private String cpf;
    private String sobre;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel_Aux() {
        return cel_aux;
    }

    public void setCel_Aux(String cel_aux) {
        this.cel_aux = cel_aux;
    }

    public String getCel() {
        return this.cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobre() {
        return this.sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    @Override
    public String toString() {
        return "Atributos{" + "nome=" + nome + '}';
    }  
}