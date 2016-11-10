/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

import br.ufpr.cfstore.jdbc.DBConnector;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe define atributos e metodos de Pessoa.
 * @author Regis
 */
public class Pessoa {
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String documento;
    private Date nascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> salvarPessoa(Pessoa pessoa) {
        Connection conn = null;
        List<String> retorno = new ArrayList<>();
        try {
            String sql = "CALL SP0302(?,?,?,?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "1");
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getSobrenome());
            stmt.setString(4, pessoa.getDocumento());
            stmt.setString(5, pessoa.getEmail());
            stmt.setString(6, pessoa.getTelefone());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            retorno.add(rs.getString("out_cod"));
            retorno.add(rs.getString("out_msg"));
        }catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
