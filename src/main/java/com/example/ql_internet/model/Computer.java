package com.example.ql_internet.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Computer {
    private String name;
    private String username;
    private Status status; // Sửa String -> Status
    private BigDecimal price;
    private long remainingMinutes;
    private String service_name;
    private LocalDateTime timeUse;
    private BigDecimal dongia;
    public Computer() {
    }

    public Computer(String name, String username, Status status, double price) {
        this.name = name;
        this.username = username;
        this.status = status;
        this.price = BigDecimal.valueOf(price);
    }

    public Computer(String name, String status, double price) {
        this.name = name;
        this.status = Status.fromMoTa(status); // ✅ Chuẩn

        this.price = BigDecimal.valueOf(price);

    }


    public Computer(String name, String username, Status status, BigDecimal price) {
        this.name = name;
        this.username = username;
        this.status = status;
        this.price = price;
    }



    public BigDecimal getDongia() {
        return dongia;
    }
    public void setDongia(BigDecimal dongia) {
        this.dongia = dongia;
    }

    public enum Status {
        DANG_SU_DUNG("on"),
        DA_KET_THUC("off");

        private final String moTa;

        Status(String moTa) {
            this.moTa = moTa;
        }

        public String getMoTa() {
            return moTa;
        }

        public static Status fromMoTa(String moTa) {
            for (Status s : Status.values()) {
                if (s.getMoTa().equalsIgnoreCase(moTa)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Không tìm thấy trạng thái: " + moTa);
        }
    }

    // Getter Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public long getRemainingMinutes() { return remainingMinutes; }
    public void setRemainingMinutes(long remainingMinutes) { this.remainingMinutes = remainingMinutes; }
    public String getServiceName() {
        return service_name;
    }

    public void setServiceName(String service_name) {
        this.service_name = service_name;
    }
    public LocalDateTime getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(LocalDateTime timeUse) {
        this.timeUse = timeUse;
    }


    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
