package com.example.ql_internet.controller;

import com.example.ql_internet.model.Computer;
import com.example.ql_internet.repository.ComputerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/computer")
public class ComputerController extends HttpServlet {
    ComputerRepository computerRepository = new ComputerRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showlist":
                showlist(req, resp);
                break;
            case "show":
                showaddComputer(req, resp);
                break;
            case "delete":
                try {
                    String username = req.getParameter("username");
                    computerRepository.deleteComputer(username);
                    resp.sendRedirect(req.getContextPath() + "/computer?action=showlist");
                } catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("error", "❌ Xoá không thành công: " + e.getMessage()); // Thêm dòng này
                    req.getRequestDispatcher("/view/showComputer.jsp").forward(req, resp);
                }
                break;
                case "edit":
                    showEditComputer(req, resp);
                    break;
            default:
                showlist(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addComputer(req, resp);
                break;
            case "update":
                update(req, resp);
                break;

        }
    }

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {// Hoặc set thêm dữ liệu nếu cần
        List<Computer> computers = computerRepository.getComputers();
        req.setAttribute("computers", computers);
        req.getRequestDispatcher("/view/showComputer.jsp").forward(req, resp);
    }

    private void showaddComputer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/addComputer.jsp").forward(req, resp);
    }

    private void addComputer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String username = req.getParameter("username");
            String statusParam = req.getParameter("status");
            double price = Double.parseDouble(req.getParameter("price"));

            Computer.Status status;

            // Nếu không có status thì mặc định là off
            if (statusParam == null || statusParam.isEmpty()) {
                status = Computer.Status.DA_KET_THUC; // off
            } else {
                status = Computer.Status.fromMoTa(statusParam); // on/off
            }

            Computer computer = new Computer(name, username, status, price);
            computerRepository.addComputer(computer);

            resp.sendRedirect(req.getContextPath() + "/computer?action=showlist");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/view/addComputer.jsp").forward(req, resp);
        }
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name"); // Máy cũ (không đổi)
            String statusParam = req.getParameter("status"); // Trạng thái mô tả (on/off)
            double price = Double.parseDouble(req.getParameter("price"));

            Computer computer = new Computer(name, statusParam, price);
            boolean success = computerRepository.updateComputer(computer);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/computer?action=showlist");
            } else {
                req.setAttribute("error", "❌ Không tìm thấy máy để cập nhật!");
                req.getRequestDispatcher("/view/updataComputer.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/view/updataComputer.jsp").forward(req, resp);
        }
    }


    private void showEditComputer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");  // Lấy name từ URL
        Computer computer = computerRepository.findByName(name);  // Tìm máy tính

        if (computer != null) {
            req.setAttribute("computer", computer);  // Đẩy lên JSP với tên "computer"
            req.getRequestDispatcher("/view/updataComputer.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Không tìm thấy máy tính!");
            resp.sendRedirect(req.getContextPath() + "/computer?action=showlist");
        }
    }

}
