/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

import br.ufpr.cfstore.jdbc.DBConnector;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Produto implements Serializable {
    private int id;
    private String nome;
    private String descricao;
    private boolean status;
    private double precoUnitario;
    private String imagem;
    private String url;

    /*
    Início Getters and Setters.
     */    
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
            String sql = "CALL SP0101(?)";
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
    
    
    /**
     * Este metodo busca as recomendacoes pelo id do produto"
     * @param idProduto
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public List<Produto> buscarRecomendacoes(int idProduto) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        List<Produto> recomendacoes = new ArrayList();
        String sql = "SELECT a.itemRecomendado, b.produto, b.precoVenda from recomendacao a inner join \n" +
                        "produto b on a.itemRecomendado = b.idProduto and a.item = ?;";
        conn = DBConnector.getConnection();
        
         try{
            conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Produto dbProduto = new Produto();
                dbProduto.setId(rs.getInt("itemRecomendado"));
                dbProduto.setDescricao(rs.getString("produto"));
                dbProduto.setPrecoUnitario(rs.getDouble("precoVenda"));
                dbProduto.setUrl("/CFStore/LojaController?action=exibir&id=" + dbProduto.getId());
                String idItem = String.valueOf(dbProduto.id);
                String img = "https://dpxzap9aaf4qu.cloudfront.net/" + idItem.substring(0, idItem.length() -2) +"00/" + idItem + "/" + idItem + "_18_zoom_180.jpg" ;
                dbProduto.setImagem(img);
                recomendacoes.add(dbProduto);
            }
            
            
        } catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recomendacoes;
    }
     /**
     * Este metodo busca pelo id"
     * @param idProduto
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public Produto retornaProduto(int idProduto) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        String sql = "SELECT produto, precoVenda from produto where idProduto = ?";
        conn = DBConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProduto);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Produto p = new Produto();
        p.setId(idProduto);
        p.setDescricao(rs.getString("produto"));
        p.setPrecoUnitario(rs.getFloat("precoVenda"));
        p.setUrl("/CFStore/LojaController?action=exibir&id=" + idProduto);
        String idItem = String.valueOf(idProduto);
        String img = "https://dpxzap9aaf4qu.cloudfront.net/" + idItem.substring(0, idItem.length() -2) +"00/" + idItem + "/" + idItem + "_18_zoom_180.jpg" ;
        p.setImagem(img);
        return p;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
