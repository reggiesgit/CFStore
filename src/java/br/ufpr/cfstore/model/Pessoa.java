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
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String documento;
    private String senha;
    private Date nascimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * Este metodo persiste um objeto do tipo Pessoa no banco de dados.
     * Ação CRUD = 1.
     * @param pessoa
     * @return 
     */
    public List<String> salvarPessoa(Pessoa pessoa) {
        Connection conn = null;
        List<String> retorno = new ArrayList<>();
        try {
            String sql = "CALL SP0302(?,?,?,?,?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "1");
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getSobrenome());
            stmt.setString(4, pessoa.getDocumento());
            stmt.setString(5, pessoa.getEmail());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(6, pessoa.getSenha());
            stmt.setString(7, pessoa.getTelefone());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            retorno.add(rs.getString("out_cod"));
            retorno.add(rs.getString("out_msg"));
            stmt.close();
            conn.close();
        }catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Este metodo busca um objeto do tipo Pessoa do banco de dados.
     * Ação CRUD = 2.
     * @param pessoa
     * @return 
     */
    public Pessoa listarPessoa(Pessoa pessoa) {
        Connection conn = null;
        Pessoa retorno = new Pessoa();
        try {
            String sql = "CALL SP0302(?,?,?,?,?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "2");
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, pessoa.getDocumento().equals("") ? null : pessoa.getDocumento());
            stmt.setString(5, pessoa.getEmail().equals("") ? null : pessoa.getEmail());
            stmt.setString(6, null);
            stmt.setString(7, null);
            ResultSet rs = stmt.executeQuery();
            rs.next(); 
            if (rs.getString("out_cod").equals("O")) {
                retorno.setNome(rs.getString("nome"));
                retorno.setSobrenome(rs.getString("sobrenome"));
                retorno.setDocumento(rs.getString("documento"));
                retorno.setEmail(rs.getString("email"));
                retorno.setTelefone(rs.getString("telefone"));
            }
            stmt.close();
            conn.close();
            return retorno;
        }catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    /**
     * Este metodo autentica um usuário comparando as informações de entrada
     * com as informações criptografadas presentes no banco de dados.
     * @param reqUser
     * @return 
     */
    public List<String> autenticar(Pessoa reqUser) {
        Connection conn = null;
        List<String> retorno = new ArrayList<>();
        try {
            String sql = "CALL SP0305(?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "2");
            stmt.setString(2, reqUser.getEmail());
            stmt.setString(3, reqUser.getSenha());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            retorno.add(rs.getString("out_cod"));
            retorno.add(rs.getString("out_msg"));
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Ocorreu um erro ao acessaro o banco: " + sqle);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
