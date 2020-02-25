package by.javatr.library.service;

import by.javatr.library.bean.User;
import by.javatr.library.service.exception.ServiceException;

import java.util.List;

public interface ClientService {

    User signIn(String login, String password) throws ServiceException;
    void signOut() throws ServiceException;
    User registration(String name, String login, String password, int year) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    void removeUser(int id)throws ServiceException;
    User getUserById(int id) throws ServiceException;
    User getAuthorizedUser();


}
