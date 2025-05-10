package com.example.ql_internet.service;

import com.example.ql_internet.model.Login;
import com.example.ql_internet.repository.LoginRepository;

import java.sql.SQLException;
import java.util.List;

public interface ILoginService {
    List<Login> getLoginsByUsername(String username) throws SQLException;
    List<Login> getLoginsByPassword(String password);
    List<Login> getLogins();
    Login add (Login login) throws SQLException;
    boolean byUsername(String username) throws SQLException;
}
;