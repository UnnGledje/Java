package com.example.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import static java.awt.SystemColor.text;

@WebServlet(name = "NumberOfHitsServlet", value = "/NumberOfHitsServlet")

public class NumberOfHitsServlet extends HttpServlet {

    private String message;
    private int numberOfHits = 0;

    public void init() {
        message = "Number Of Hits";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"+"</h1>");
        out.println("<h2 style=\"text-align:center;\">" + message + "</h2>");
        out.println("<h3 style=\"text-align:center;\">" + numberOfHits++ + "</h3>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
