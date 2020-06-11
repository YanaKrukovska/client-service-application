package ua.edu.ukma.ykrukovska.practice5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StorageServlet", urlPatterns = {"/login", "/api/good/{id}", "/api/good"})
public class StorageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGetRequest(request, response);
    }

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletPath();
        switch (path) {
            case "login":
                getLogin(request, response);
                break;
            case "api/good/{id}":
                getGoods(request, response);
                break;
        }
    }

    private void getGoods(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getLogin(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Unique token", TokenGenerator.createJWT("0", login, "?", 0));
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}