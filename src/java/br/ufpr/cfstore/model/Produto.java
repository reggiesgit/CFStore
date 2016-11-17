/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

import br.ufpr.cfstore.jdbc.DBConnector;
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
public class Produto {
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
     * @param pagina
     * @return 
     */
    public List<Produto> listarProdutos(String fragmento, int pagina) {
        List<Produto> variosProduto = new ArrayList();
        Connection conn = null;
        
        try{
            String sql = "CALL SP0101(?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, fragmento);
            stmt.setInt(2, pagina);
            stmt.setInt(3, 0);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Produto dbProduto = new Produto();
                dbProduto.setId(Integer.parseInt(rs.getString("idProduto")));
                String idItem = String.valueOf(dbProduto.id);
                String img = "https://dpxzap9aaf4qu.cloudfront.net/" + idItem.substring(0, idItem.length() -2) +"00/" + idItem + "/" + idItem + "_18_zoom_180.jpg" ;
                dbProduto.setImagem(img);                
                dbProduto.setUrl("Loja?action=exibir&id=" + idItem);
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
     * Este metodo busca as recomendacoes pelo id do produto
     * @param idProduto
     * @return 
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
                dbProduto.setUrl("/CFStore/Loja?action=exibir&id=" + dbProduto.getId());
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
     * Este metodo busca um produto pelo id
     * @param idProduto
     * @return 
     */
    public Produto retornaProduto(int idProduto) throws ClassNotFoundException, SQLException{
        Connection conn = null;
    }
    
    /**
     * Este metodo adiciona um item no carrinho.
     * O carrinho é identificado pelo ID da sessão se não houver um login ativo.
     * Quando o cliente faz login, o ID da sessão é atrelado ao seu ID.
     * @param sessionID
     * @param clienteID
     * @param idToAdd
     * @param unidades
     * @return 
     */
    public List<String> adicionarNoCarrinho(String sessionID, int clienteID, int idToAdd, int unidades) {
        Connection conn = null;
        List<String> retorno = new ArrayList<>();
        try{
            String sql = "CALL SP0303(?,?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, 1);
            stmt.setString(2, sessionID);
            stmt.setInt(3, clienteID);
            stmt.setInt(4, idToAdd);
            stmt.setInt(5, unidades);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            retorno.add(rs.getString("out_cod"));
            retorno.add(rs.getString("out_msg"));
        } catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     * Este metodo remove itens do carrinho a partir do ID do item.
     * O carrinho é identificado pelo ID da sessão se não houver um login ativo.
     * Quando o cliente faz login, o ID da sessão é atrelado ao seu ID.
     * @param sessionID
     * @param clienteID
     * @param idToAdd
     * @param unidades
     * @return 
     */
    public List<String> removerDoCarrinho(String sessionID, int clienteID, int idToAdd, int unidades) {
        Connection conn = null;
        List<String> retorno = new ArrayList<>();
        try{
            String sql = "CALL SP0303(?,?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, 3);
            stmt.setString(2, sessionID);
            stmt.setInt(3, clienteID);
            stmt.setInt(4, idToAdd);
            stmt.setInt(5, unidades);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            retorno.add(rs.getString("out_cod"));
            retorno.add(rs.getString("out_msg"));
        } catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    public List <Produto> listarPorDepto(String depto, int p){
    List<Produto> variosProduto = new ArrayList();
        Connection conn = null;
        
        try{
            String sql = "CALL listarPorDepto(?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, depto);
            stmt.setInt(2, p);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Produto dbProduto = new Produto();
                dbProduto.setId(Integer.parseInt(rs.getString("idProduto")));
                String idItem = String.valueOf(dbProduto.id);
                String img = "https://dpxzap9aaf4qu.cloudfront.net/" + idItem.substring(0, idItem.length() -2) +"00/" + idItem + "/" + idItem + "_18_zoom_180.jpg" ;
                dbProduto.setImagem(img);                
                dbProduto.setUrl("Loja?action=exibir&id=" + idItem);
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
