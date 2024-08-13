/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.controlador;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private ClienteDao clienteDao = new ClienteDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

Cliente cliente = clienteDao.buscarPorUsername(username);

        if (cliente != null && cliente.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("cliente", cliente);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

