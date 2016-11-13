/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.controller;

import br.ufpr.cfstore.model.ItemPedido;
import br.ufpr.cfstore.model.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Este controller gerencia as ações da página inicial da loja, decidindo
 * buscas, adição ao carrinho, finalização de compras, etc...
 *
 * @author Regis
 */
@WebServlet(name = "LojaController", urlPatterns = {"/LojaController"})
public class LojaController extends HttpServlet {

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
        
        /* Captura de parâmetros vindos do navegador, que definem as funções solicitadas pelo usuário. */
        HttpSession session = request.getSession(false);
        String reqFragmento = request.getParameter("fragmento");
        String action = request.getParameter("action");
        int pagina = 1;

        /* Decisões de ação */
        switch (action) {
            /* Busca produtos por nome, fornecedor, categoria, sub categoria, cor, etc... */
            case "buscar":
                Produto produto = new Produto();
                List<Produto> dbProdutos = produto.listarProdutos(reqFragmento, pagina);
                if(!dbProdutos.isEmpty()) {
                    request.setAttribute("dbProdutos", dbProdutos);
                    rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                }
                break;
            /* Abre a página de detalhes do protudo selecionado, possibilita adicionar ao carrinho*/
            case "verDetalhe":
                int idDetalhe = Integer.parseInt(request.getParameter("idDetalhe"));
                Produto dbProduto = new Produto();
                List<Produto> recomendacao = dbProduto.buscarRecomendacoes(idDetalhe);
                dbProduto = dbProduto.retornaProduto(idDetalhe);
                request.setAttribute("produto", dbProduto);
                rd = getServletContext().getRequestDispatcher("/detalhe.jsp");
                rd.forward(request, response); 
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
