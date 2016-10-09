/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.model;

/**
 * Esta classe define atributos e metodos do Fornecedor
 * @author Regis
 */
public class Fornecedor {
    private String razaoFornecedor;
    private String ufFornecedor;
    private String cidadeFornecedor;
    private int referenciaFornecedor;

    public String getRazaoFornecedor() {
        return razaoFornecedor;
    }

    public void setRazaoFornecedor(String razaoFornecedor) {
        this.razaoFornecedor = razaoFornecedor;
    }

    public String getUfFornecedor() {
        return ufFornecedor;
    }

    public void setUfFornecedor(String ufFornecedor) {
        this.ufFornecedor = ufFornecedor;
    }

    public String getCidadeFornecedor() {
        return cidadeFornecedor;
    }

    public void setCidadeFornecedor(String cidadeFornecedor) {
        this.cidadeFornecedor = cidadeFornecedor;
    }

    public int getReferenciaFornecedor() {
        return referenciaFornecedor;
    }

    public void setReferenciaFornecedor(int referenciaFornecedor) {
        this.referenciaFornecedor = referenciaFornecedor;
    }
    
     
}
