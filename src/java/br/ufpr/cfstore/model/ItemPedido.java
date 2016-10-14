/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

/**
 *
 * @author Regis
 */
public class ItemPedido {
    private String id;
    private Produto item;
    private double precoUnitario;
    private int quantia;

    /*
    Início Getters and Setters.
     */  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produto getItem() {
        return item;
    }

    public void setItem(Produto item) {
        this.item = item;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantia() {
        return quantia;
    }

    public void setQuantia(int quantia) {
        this.quantia = quantia;
    }
    /*
    Fim Getters and Setters, Início CRUD de Produtos.
    */   
}
