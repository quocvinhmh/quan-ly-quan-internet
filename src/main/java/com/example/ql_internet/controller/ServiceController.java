package com.example.ql_internet.controller;

import com.example.ql_internet.model.Service;
import com.example.ql_internet.repository.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServiceController", value = "/service")
public class ServiceController extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "list":
                listServices(req, resp);
                break;
            case "showupdate":
                showUpdateForm(req, resp);
                break;
            case "showadd":
                showAddForm(req, resp);
                break;
            case "delete":
                deleteService(req, resp);
                break;
            default:
                listServices(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addService(req, resp);
                break;
            case "update":
                updateService(req, resp);
                break;
        }
    }

    private void listServices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> services = serviceRepository.getServiceList();
        req.setAttribute("services", services);
        req.getRequestDispatcher("/view/showService.jsp").forward(req, resp);
    }

    private void addService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // ✅ BẮT BUỘC PHẢI CÓ ĐỂ TIẾNG VIỆT ĐÚNG

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            Service service = new Service(id, name, price);

            boolean addSuccess = serviceRepository.addService(service);
            if (addSuccess) {
                resp.sendRedirect(req.getContextPath() + "/service?action=list");
            } else {
                req.setAttribute("error", "❌ Thêm dịch vụ thất bại!");
                req.getRequestDispatcher("/view/addService.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "❌ Lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/addService.jsp").forward(req, resp);
        }
    }


    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/addService.jsp").forward(req, resp);
    }

    private void updateService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 🛑 BẮT BUỘC PHẢI CÓ DÒNG NÀY

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));

            Service service = new Service(id, name, price);
            boolean updateSuccess = serviceRepository.updateService(id, service);

            if (updateSuccess) {
                resp.sendRedirect(req.getContextPath() + "/service?action=list");
            } else {
                req.setAttribute("error", "❌ Cập nhật thất bại!");
                req.setAttribute("service", service);
                req.getRequestDispatcher("/view/updateService.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "❌ Lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/updateService.jsp").forward(req, resp);
        }
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Service service = serviceRepository.findById(id);
            if (service != null) {
                req.setAttribute("service", service);
                req.getRequestDispatcher("/view/updataService.jsp").forward(req, resp);
            } else {
                // THIẾU DÒNG NÀY GÂY RA LIST RỖNG:
                req.setAttribute("services", serviceRepository.getServiceList());
                req.setAttribute("error", "❌ Không tìm thấy dịch vụ cần cập nhật.");
                req.getRequestDispatcher("/view/showService.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("services", serviceRepository.getServiceList()); // CẦN THÊM
            req.setAttribute("error", "❌ Lỗi không thực hiện được chức năng.");
            req.getRequestDispatcher("/view/showService.jsp").forward(req, resp);
        }
    }



    private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean deleteSuccess = serviceRepository.deleteService(id);
            if (deleteSuccess) {
                resp.sendRedirect(req.getContextPath() + "/service?action=list");
            } else {
                req.setAttribute("error", "❌ Xóa dịch vụ thất bại!");
                listServices(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "❌ Lỗi: " + e.getMessage());
            listServices(req, resp);
        }
    }
}
