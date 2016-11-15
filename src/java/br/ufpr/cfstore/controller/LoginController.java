/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.controller;

import br.ufpr.cfstore.model.Pessoa;
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
 * Este controller gerencia a atutenticação: login e logoff.
 *
 * @author Regis
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        String action = request.getParameter("action");

        //Usuário que vem do navegador
        Pessoa reqUser = new Pessoa();
        reqUser.setEmail(request.getParameter("email"));
        reqUser.setSenha(request.getParameter("pass"));

        if ("logoff".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                request.setAttribute("message", "Sua sessão foi encerrada.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            }
        } else {
            List<String> resultado = reqUser.autenticar(reqUser);
            if (resultado.get(1).equals("O")) {

                HttpSession sessao = request.getSession(false);

                Pessoa usuarioCorrente = reqUser.listarPessoa(reqUser);
                sessao.setAttribute("usuarioCorrente", usuarioCorrente);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/PortalController");
                rd.forward(request, response);
            } else if (resultado.get(1).equals("A")) {
                request.setAttribute("message", resultado.get(2));
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                rd.include(request, response);
            } else {
                request.setAttribute("message", resultado.get(2));
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/mostrar.jsp");
                rd.include(request, response);
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
