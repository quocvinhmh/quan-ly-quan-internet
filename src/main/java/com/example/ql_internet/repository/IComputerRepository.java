package com.example.ql_internet.repository;

import com.example.ql_internet.model.Computer;

import java.util.List;

public interface IComputerRepository {
    List<Computer> getComputers();
    Computer addComputer(Computer computer);
    boolean updateComputer(Computer  name);
    boolean deleteComputer(String username);

    Computer findByName(String name);
}
