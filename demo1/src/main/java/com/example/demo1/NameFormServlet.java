package com.example.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NameFormServlet", value = "/NameFormServlet")
public class NameFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String fullAddress = request.getParameter("address");

        PrintWriter printer = response.getWriter();
        printer.println("<h1>Hello " + fullName + " I know where you live: " + fullAddress + "</h1>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String fullAddress = request.getParameter("address");

        PrintWriter printer = response.getWriter();
        printer.println("<h1>Hello " + fullName + " I know where you live: " + fullAddress + "</h1>");
    }
}
