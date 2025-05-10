
package com.example.ql_internet.controller;

import com.example.ql_internet.model.Login;
import com.example.ql_internet.model.Manage;
import com.example.ql_internet.repository.LoginRepository;
import com.example.ql_internet.repository.ManageRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet {
    ManageRepository manageRepository = new ManageRepository();
    LoginRepository loginRepository = new LoginRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    manageRepository.delManageByid(id);
                    String username = request.getParameter("username");

                    if (loginRepository.byUsername(username)) {
                        response.sendRedirect(request.getContextPath() + "/admin?action=list");
                    } else {
                        request.setAttribute("error", "❌ Không thể xoá tài khoản!");
                        request.getRequestDispatcher("/view/admin.jsp").forward(request, response); // ✅ forward khi lỗi
                    }

                    break;

                case "showadd":
                    request.getRequestDispatcher("/view/showadd.jsp").forward(request, response);
                    break;
                default:
                    List<Manage> manages = manageRepository.getManage();
                    request.setAttribute("manages", manages);
                    request.getRequestDispatcher("/view/admin.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                String name = req.getParameter("name");
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String role = req.getParameter("role");

                if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                    throw new RuntimeException("Tên đăng nhập và mật khẩu không được để trống!");
                }

                Login login = new Login(username, password, role);
                loginRepository.addLogin(login); // có kiểm tra username trùng

                Manage manage = new Manage(name, username, role);
                manageRepository.addManage(manage);

                resp.sendRedirect("admin?action=list");

            } catch (Exception e) {
                e.printStackTrace(); // log ra console
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/view/showadd.jsp").forward(req, resp);
            }
        }
    }
}