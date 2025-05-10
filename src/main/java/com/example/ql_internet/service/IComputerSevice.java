package com.example.ql_internet.service;

import com.example.ql_internet.model.Computer;

import java.util.List;

public interface IComputerSevice {
    List<Computer> getComputers();
    Computer addComputer(Computer computer);
    Computer updateComputer(Computer username);
    boolean deleteComputer(String username);
    Computer findByName(String name);
}
