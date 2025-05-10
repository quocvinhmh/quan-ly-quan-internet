package com.example.ql_internet.controller;

import com.example.ql_internet.model.Login;
import com.example.ql_internet.repository.LoginRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "Đăng nhập",value = "/Login")
public class LoginController extends HttpServlet {
    LoginRepository loginRepository = new LoginRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        if (account == null) {
            account = "";
        }
        switch (account) {
            case "Login":
ShowLogin(req, resp);
break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        if (account == null) {
            account = "";
        }
        switch (account) {
            case "success":
                try {
                    SuccessLogin(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException("⚠️ Lỗi khi kiểm tra đăng nhập: " + e.getMessage(), e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void ShowLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    private void SuccessLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
String username = req.getParameter("username");
String password = req.getParameter("password");
        Login login = loginRepository.checkLogin(username,password);
        if (login != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", login.getUsername());
            session.setAttribute("role", login.getRole());
            req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
        }else {
            req.setAttribute("message", "❌ Tên đăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
