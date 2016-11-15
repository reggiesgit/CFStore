/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.controller;

import br.ufpr.cfstore.model.Endereco;
import br.ufpr.cfstore.model.Pessoa;
import br.ufpr.cfstore.tools.Encrypter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este controller gerencia Pessoas; chamando operações de CRUD de pessoa.
 * 
 * @author Regis
 */
public class PessoaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        List<String> message = new ArrayList<>();
        
        /* Captura de parâmetros vindos do navegador, que definem as funções solicitadas pelo usuário. */
        String action = request.getParameter("action");
        
        /* Criptografar senha */
        Encrypter enc = new Encrypter();
        String password = request.getParameter("senha");
        enc.shuffle(password);
        
        switch(action) {
            case "cadastrar" :
                Pessoa pessoa = new Pessoa();
                Endereco endereco = new Endereco();
                pessoa.setDocumento(request.getParameter("documento"));
                pessoa.setNome(request.getParameter("nome"));
                pessoa.setSobrenome(request.getParameter("sobrenome"));
                pessoa.setEmail(request.getParameter("email"));
                pessoa.setSenha(password);
                pessoa.setTelefone(request.getParameter("celular"));
                endereco.setCep(request.getParameter("CEP"));
                endereco.setRua(request.getParameter("rua"));
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                endereco.setComplemento(request.getParameter("complemento"));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCidade(request.getParameter("cidade"));
                endereco.setEstado(request.getParameter("estado"));
                /* Tenta salvar a pessoa no banco. Armazena o resultado da operação na variável 'message' */
                message = (pessoa.salvarPessoa(pessoa));
                /* Se o codigo do retorno for 'O', tenta salvar o endereço com a FK da pessoa */
                if(message.get(0).equals("O")) {
                    if (endereco.salvarEndereco(endereco, pessoa.getDocumento()).get(0).equals("O")) {
                        /* Se chegou até aqui, é sinal que deu tudo certo, e pode retornar para o usuário */
                        request.setAttribute("message", message.get(1));
                        rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                        rd.forward(request, response);
                        break;
                    }
                    else {
                        request.setAttribute("message", message.get(1));
                        rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                        rd.forward(request, response);
                        break;
                    }
                    
                }
                else {
                    request.setAttribute("message", message.get(1));
                    rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                    rd.forward(request, response);
                    break;
                }
                
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
