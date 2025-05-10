package com.example.ql_internet.service;

import com.example.ql_internet.model.Computer;
import com.example.ql_internet.repository.ComputerRepository;

import java.util.List;

public class ComputerService implements IComputerSevice{
 ComputerRepository computerRepository = new ComputerRepository();

    @Override
    public List<Computer> getComputers() {
        return computerRepository.getComputers();
    }

    @Override
    public Computer addComputer(Computer computer) {
        return computerRepository.addComputer(computer);
    }

    @Override
    public Computer updateComputer(Computer username) {
        return computerRepository.addComputer(username);
    }

    @Override
    public boolean deleteComputer(String username) {
        return computerRepository.deleteComputer(username);
    }

    @Override
    public Computer findByName(String name) {
        return computerRepository.findByName(name);
    }
}
