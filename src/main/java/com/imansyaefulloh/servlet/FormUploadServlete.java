package com.imansyaefulloh.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class FormUploadServlete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (InputStream inputStream = FormServlet.class.getResourceAsStream("/html/form-upload.html");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line).append("\n");
            }
            resp.getWriter().println(html.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part profile = req.getPart("profile");

        Path uploadLocation = Path.of("upload/" + UUID.randomUUID().toString() + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), uploadLocation);

        String html = """
                <html>
                <body>
                Name : $name
                <br>
                Profile : <img width="400px" height="400px" src="/download?file=$profile" />
                </body>
                <html>
                """
                .replace("$name", name)
                .replace("$profile", uploadLocation.getFileName().toString());

        resp.getWriter().println(html);
    }
}
