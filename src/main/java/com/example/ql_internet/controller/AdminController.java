package com.example.ql_internet.controller;

import com.example.ql_internet.model.Manage;
import com.example.ql_internet.repository.ManageRepository;
import com.example.ql_internet.service.ManageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class ManageController extends HttpServlet {
    ManageRepository manageRepository = new ManageRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "list":
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                // Mặc định cũng hiển thị danh sách
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Manage> manages = manageRepository.getManage();
        request.setAttribute("manages", manages);
        request.getRequestDispatcher("/view/admin.jsp").forward(request, response);
    }
}