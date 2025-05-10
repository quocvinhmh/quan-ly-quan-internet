package com.example.ql_internet.controller;

import com.example.ql_internet.model.Computer;
import com.example.ql_internet.repository.ComputerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "manager_controller", value = "/manager")
public class ManageController extends HttpServlet {
    ComputerRepository computerRepository = new ComputerRepository();
    private String lastTurnedOffComputerName = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "listComputer":
                showManager(req, resp);
                break;
            case "turnon":
                turnon(req, resp);
                break;
            case "turnoff":
                turnoff(req, resp);
                break;
            case "orderComputer":
                orderComputer(req, resp);
                break;
            case "bill":
                billshow(req, resp);
                break;
            case "clear":
                clearAfterPayment(req, resp);
                break;
            default:
                showManager(req, resp);
                break;
        }
    }

    private void showManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Computer> computers = computerRepository.getComputers();
        req.setAttribute("computers", computers);
        req.getRequestDispatcher("/view/showManager.jsp").forward(req, resp);
    }

    private void turnon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");

            if (name == null || name.trim().isEmpty()) {
                throw new RuntimeException("❌ Thiếu tên máy!");
            }

            Computer computer = computerRepository.findByName(name);
            if (computer == null) {
                throw new RuntimeException("❌ Không tìm thấy máy: " + name);
            }

            computer.setStatus(Computer.Status.DANG_SU_DUNG);
            computer.setTimeUse(LocalDateTime.now());
            computer.setDongia(null);

            if (computer.getPrice() == null) {
                computer.setPrice(BigDecimal.valueOf(10000)); // Giá mặc định: 10.000 VND/giờ
            }

            boolean updated = computerRepository.updateComputer(computer);
            if (!updated) {
                throw new RuntimeException("❌ Không thể cập nhật trạng thái máy: " + name);
            }

            resp.sendRedirect(req.getContextPath() + "/manager?action=listComputer");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/view/error.jsp").forward(req, resp);
        }
    }

    private void turnoff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            Computer computer = computerRepository.findByName(name);

            if (computer == null) {
                throw new RuntimeException("❌ Không tìm thấy máy: " + name);
            }

            if (computer.getStatus() == Computer.Status.DANG_SU_DUNG) {
                if (computer.getTimeUse() == null) {
                    throw new RuntimeException("❌ Máy chưa được bật (timeUse = null), không thể tắt!");
                }

                LocalDateTime now = LocalDateTime.now();
                long durationMinutes = Duration.between(computer.getTimeUse(), now).toMinutes();
                if (durationMinutes == 0) durationMinutes = 1;

                if (computer.getPrice() == null) {
                    computer.setPrice(BigDecimal.valueOf(10000)); // Gán mặc định nếu chưa có
                }

                BigDecimal pricePerHour = computer.getPrice();
                BigDecimal pricePerMinute = pricePerHour.divide(BigDecimal.valueOf(60), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal tongTien = pricePerMinute.multiply(BigDecimal.valueOf(durationMinutes));

                System.out.println("💰 Giá mỗi giờ: " + pricePerHour);
                System.out.println("🕒 Số phút sử dụng: " + durationMinutes);
                System.out.println("💵 Tổng tiền: " + tongTien);

                computer.setDongia(tongTien);

                boolean updated = computerRepository.updateComputer(computer);
                if (!updated) {
                    throw new RuntimeException("❌ Lỗi cập nhật thông tin máy!");
                }

                lastTurnedOffComputerName = name;
            }

            resp.sendRedirect(req.getContextPath() + "/manager?action=bill&name=" + name);

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    private void orderComputer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Computer> computers = computerRepository.getComputers();

        for (Computer computer : computers) {
            if (computer.getStatus() == Computer.Status.DANG_SU_DUNG && computer.getTimeUse() != null) {
                long duration = Duration.between(computer.getTimeUse(), LocalDateTime.now()).toMinutes();
                computer.setRemainingMinutes(duration);
            } else {
                computer.setRemainingMinutes(0);
            }
        }

        req.setAttribute("computers", computers);
        req.getRequestDispatcher("/view/order_Computer.jsp").forward(req, resp);
    }

    private void billshow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("computers", new ArrayList<>());
        } else {
            Computer computer = computerRepository.findByName(name);
            List<Computer> computers = new ArrayList<>();
            if (computer != null) {
                computers.add(computer);
            }
            req.setAttribute("computers", computers);
        }
        req.getRequestDispatcher("/view/showBill.jsp").forward(req, resp);
    }

    private void clearAfterPayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            Computer computer = computerRepository.findByName(name);
            if (computer != null) {
                computer.setStatus(Computer.Status.DA_KET_THUC);
                computer.setDongia(null);
                computer.setTimeUse(null);
                computerRepository.updateComputer(computer);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/manager?action=listComputer");
    }
}
