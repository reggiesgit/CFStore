/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.controller;

import br.ufpr.cfstore.model.ItemPedido;
import br.ufpr.cfstore.model.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LojaController", urlPatterns = {"/Loja"})
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
           case "buscar":
                Produto produto = new Produto();
                List<Produto> dbProdutos = produto.listarProdutos(reqFragmento, pagina);
                if(!dbProdutos.isEmpty()) {
                    request.setAttribute("dbProdutos", dbProdutos);
                    rd = getServletContext().getRequestDispatcher("/home.jsp");
                List<Produto> dbProduto = produto.listarProdutos(reqFragmento,p);
                if(!dbProduto.isEmpty()) {
                    request.setAttribute("busca",reqFragmento);
                    request.setAttribute("dbProdutos", dbProduto);
                    rd = getServletContext().getRequestDispatcher("/index.jsp?action=buscar");
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
            case "exibir":
                int idProduto = Integer.parseInt(request.getParameter("id"));

                try {
                    Produto item = new Produto().retornaProduto(idProduto);
                    request.setAttribute("item", item);
                    String idItem = String.valueOf(item.getId());
                    String imagem = "https://dpxzap9aaf4qu.cloudfront.net/" + idItem.substring(0, idItem.length() -2) +"00/" + idItem + "/" + idItem + "_18_zoom_180.jpg" ;
                    item.setImagem(imagem);
                    List<Produto> recomendacoes = item.buscarRecomendacoes(item.getId());
                    request.setAttribute("recomendacoes", recomendacoes);
                    rd = getServletContext().getRequestDispatcher("/index.jsp");    
                    rd.forward(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LojaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LojaController.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
                case "listar":
                if(depto == null ){depto = "*";}
                dbProduto = new Produto().listarPorDepto(depto,p);
                if(!dbProduto.isEmpty()) {
                    request.setAttribute("depto",depto);
                    request.setAttribute("dbProdutos", dbProduto);
                    rd = getServletContext().getRequestDispatcher("/index.jsp?action=listar");
                    rd.forward(request, response);
                }
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
