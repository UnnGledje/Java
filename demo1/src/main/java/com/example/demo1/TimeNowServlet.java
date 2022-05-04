package com.example.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "TimeNowServlet", value = "/TimeNowServlet")
public class TimeNowServlet extends HttpServlet {

   private LocalDate date = LocalDate.now();
   private String message;

   public void init(){message = "Current Date";}




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 style=\"text-align:center;\">" + message + "</h1>");
        out.println("<h2 style=\"text-align:center;\">" + date + "</h2>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
