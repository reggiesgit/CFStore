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
    public List<Produto> buscarRecomendacoes(int idProduto) {
        Connection conn = null;
        List<Produto> recomendacoes = new ArrayList();
        String sql = "SELECT a.itemRecomendado as idProduto, b.produto, b.precoVenda from recomendacao a inner join \n" +
                        "produto b on a.itemRecomendado = b.idProduto and a.item = ?;";
         try{
            conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Produto dbProduto = new Produto();
                dbProduto.setDescricao(rs.getString("produto"));
                dbProduto.setPrecoUnitario(rs.getDouble("precoVenda"));
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
    public Produto retornaProduto(int idProduto) {
        Produto dbProduto = new Produto();
        Connection conn = null;
        try{
            String sql = "CALL SP0101(?,?,?)";
            conn = DBConnector.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "");
            stmt.setInt(2, 1);
            stmt.setInt(3, idProduto);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            dbProduto.setId(Integer.parseInt(rs.getString("idProduto")));
            dbProduto.setNome(rs.getString("produto"));
            dbProduto.setPrecoUnitario(rs.getDouble("precoReal"));
            return dbProduto;  
        } catch (SQLException sqle) {
            System.out.println("Erro ao efetuar a busca no banco de dados: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    return dbProduto;
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
}
