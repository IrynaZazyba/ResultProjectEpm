package by.javatr.library.dao;

import by.javatr.library.bean.User;
import by.javatr.library.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {

    List<User> getAll() throws DAOException;

    void save(User user) throws DAOException;

    void removeUserById(int id) throws DAOException;

    User getUserByLogin(String login) throws DAOException;
    User getUserById(int id) throws DAOException;



}
