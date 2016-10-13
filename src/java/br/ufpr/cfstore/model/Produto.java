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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe define atributos e metodos do Produto.
 * @author Regis
 */
public class Produto {
    private String nome;
    private String descricao;
    private boolean status;
    private double precoUnitario;

    /*
    Início Getters and Setters.
    */
    public String getNome() {    
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    /*
    Fim Getters and Setters, Início CRUD de Produtos.
    */
    /**
     * Este metodo busca todos os produtos ou produtos que possuem "fragmento"
     * em seu nome, assim como outros parâmetros relacionados ao mesmo; [fornecedor,
     * categoria, subCategoria]
     * @param fragmento
     * @return 
     */
    public List<Produto> listarProdutos(String fragmento) {
        List<Produto> variosProduto = new ArrayList();
        Connection conn = null;
        
        try{
            String sql = "CALL SP010101(?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, fragmento);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Produto dbProduto = new Produto();
                dbProduto.setNome(rs.getString("produto"));
                dbProduto.setPrecoUnitario(rs.getDouble("precoReal"));
                variosProduto.add(dbProduto);
            }
            return variosProduto;
            
        } catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return variosProduto;
    }
}
