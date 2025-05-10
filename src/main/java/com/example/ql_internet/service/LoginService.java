package com.example.ql_internet.service;

import com.example.ql_internet.model.Login;
import com.example.ql_internet.repository.LoginRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginService implements ILoginService {
    LoginRepository loginRepository = new LoginRepository();
    @Override
    public List<Login> getLoginsByUsername(String username) throws SQLException {
        return loginRepository.getLoginsByUsername(username);
    }

    @Override
    public List<Login> getLoginsByPassword(String password) {
        return loginRepository.getLoginsByPassword(password);
    }

    @Override
    public List<Login> getLogins() {
        return loginRepository.getLogins();
    }

    @Override
    public Login add(Login login) throws SQLException {
        return loginRepository.addLogin(login);
    }

    @Override
    public boolean byUsername(String username) throws SQLException {
        return loginRepository.byUsername(username);
    }
}
