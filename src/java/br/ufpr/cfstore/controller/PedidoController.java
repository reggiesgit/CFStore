/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.controller;

import br.ufpr.cfstore.model.ItemPedido;
import br.ufpr.cfstore.model.Pessoa;
import br.ufpr.cfstore.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Regis
 */
@WebServlet(name = "CarrinhoController", urlPatterns = {"/CarrinhoController"})
public class PedidoController extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        switch (action) { 
            /* Adiciona itens ao carrinho, que é identificado pelo ID da sessão se nenhum login estive ativo */
            case "addCarrinho":
                int idToAdd = Integer.parseInt(request.getParameter("idToAdd"));
                int unidades = Integer.parseInt(request.getParameter("unidades"));
                Pessoa usuario = new Pessoa();
                usuario = (Pessoa) session.getAttribute("usuarioCorrente");
                Produto produto = new Produto();
                produto.adicionarNoCarrinho(session.getId(), usuario.getId(), idToAdd, unidades);
                request.setAttribute("message", "Item adicionado com sucesso!");
                rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                rd.forward(request, response);
                break;
            /* Remove itens do carrinho, que é identificado pelo ID da sessão se nenhum login estiver ativo */
            case "listar" :
                ItemPedido pedidos = new ItemPedido();
                pedidos = (ItemPedido) session.getAttribute("itens");
                request.setAttribute("itens", pedidos);
                rd = getServletContext().getRequestDispatcher("/carrinho.jsp");
                rd.forward(request, response);
                break;
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
