package ua.edu.ukma.ykrukovska.practice5;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.edu.ukma.ykrukovska.practice2.storage.Product;
import ua.edu.ukma.ykrukovska.practice4.StorageRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyHttpHandler implements HttpHandler {

    private StorageRepository storageRepository = new StorageRepository();
    private static Connection dbConnection;

    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange response) throws IOException {

        storageRepository.connect();
        dbConnection = storageRepository.getConnection();

        if ("GET".equals(response.getRequestMethod())) {

            if (response.getRequestURI().getPath().equals("/login")) {
                doGetLogin(response);
            } else if (response.getRequestURI().getPath().equals("/api/good")) {
                doGetGoods(response);
            }

        } else if ("DELETE".equals(response.getRequestMethod())) {
            doDelete(response);
        }

    }

    private void doDelete(HttpExchange response) throws IOException {
        Map<String, String> params = queryToMap(response.getRequestURI().getQuery());
        long id = Long.parseLong(params.get("id"));
        ResultSet resultProduct = storageRepository.findProductById(id);
        try {
            storageRepository.delete(resultProduct.getString("product_name"));
            response.sendResponseHeaders(HttpServletResponse.SC_NO_CONTENT, -1);
        } catch (SQLException e) {
            response.sendResponseHeaders(HttpServletResponse.SC_NOT_FOUND, -1);
        }
    }

    private void doGetGoods(HttpExchange response) throws IOException {
        Map<String, String> params = queryToMap(response.getRequestURI().getQuery());
        ResultSet productResult = storageRepository.findProductById(Integer.parseInt(params.get("id")));
        Product product = null;
        try {
            product = new Product(productResult.getString("product_name"), new LinkedList<>(),
                    productResult.getInt("amount"), productResult.getDouble("price"));
        } catch (SQLException e) {
            response.sendResponseHeaders(HttpServletResponse.SC_NOT_FOUND, -1);
        }

        String goodsResponse = this.gson.toJson(product);
        OutputStream outputStream = response.getResponseBody();
        response.sendResponseHeaders(HttpServletResponse.SC_OK, goodsResponse.length());
        outputStream.write(goodsResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void doGetLogin(HttpExchange httpExchange) throws IOException {
        Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery());
        String login = params.get("username");
        String password = params.get("password");

        if (login.equals("erzhan")) {
            httpExchange.sendResponseHeaders(HttpServletResponse.SC_UNAUTHORIZED, -1);
        } else {
            String htmlResponse = TokenGenerator.createJWT("0", login, "?", 0);
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(HttpServletResponse.SC_OK, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    private Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

}