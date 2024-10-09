package com.imansyaefulloh.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Method : " + req.getMethod());
        resp.getWriter().println("URI : " + req.getRequestURI());
        resp.getWriter().println("Query : " + req.getQueryString());
        resp.getWriter().println("Path : " + req.getPathInfo());
        resp.getWriter().println("Context Path : " + req.getContextPath());
        resp.getWriter().println("Servlet Path : " + req.getServletPath());
    }
}
