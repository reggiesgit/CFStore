/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

import java.util.Date;
import java.util.List;

/**
 * Esta classe define atributos e metodos do Pedido.
 * @author Regis
 */
public class Pedido extends Pessoa {
    private Date dataPedido;
    private Date dataAtualizacao;
    private boolean aprovado;
    private List <ItemPedido> itens;

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public List <ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List <ItemPedido> itens) {
        this.itens = itens;
    }
    
    public void adicionaNoCarrinho(ItemPedido item){
        
    }

       
}
