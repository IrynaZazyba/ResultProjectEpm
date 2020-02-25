package by.javatr.library.service.impl;

import by.javatr.library.dao.util.Role;
import by.javatr.library.dao.util.Status;
import by.javatr.library.bean.User;
import by.javatr.library.dao.UserDAO;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.exception.*;
import by.javatr.library.dao.factory.DAOFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.validator.UserValidator;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDAO();

    private User currentUser = null;

    @Override
    public User getAuthorizedUser() {
        return currentUser;
    }


    @Override
    public User signIn(String login, String password) throws ServiceException {

        if (!UserValidator.validateLogin(login)) {
            throw new ServiceUserLogicException("Login is incorrect");
        }

        if (!UserValidator.validatePassword(password)) {
            throw new ServiceUserLogicException("Password is incorrect");
        }

        User user;

        try {
            user = userDAO.getUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                user.setStatus(Status.ONLINE);
                currentUser = user;
                return user;
            } else {
                throw new ServiceUserNotFoundException("Such user doesn't exist. Or password is incorrect.");
            }
        } catch (DAOException e) {
            throw new ServiceUserNotFoundException("Such user doesn't exist. Or password is incorrect.",e);
        }
    }

    @Override
    public void signOut() {

        if (currentUser.getStatus().equals(Status.ONLINE)) {
            currentUser.setStatus(Status.OFFLINE);
            currentUser = null;
        }
    }

    @Override
    public User registration(String name, String login, String password, int year) throws ServiceException {

        if (!UserValidator.validateLogin(login)) {
            throw new ServiceUserLogicException("Login is incorrect");
        }

        if (!UserValidator.validatePassword(password)) {
            throw new ServiceUserLogicException("Password is incorrect");
        }

        if (!UserValidator.validateName(name)) {
            throw new ServiceUserLogicException("Name is incorrect");
        }

        if (!UserValidator.validateYear(year)) {
            throw new ServiceUserLogicException("Year is incorrect");
        }


        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setYear(year);
        user.setRole(Role.USER);
        try {
            userDAO.save(user);
            user.setStatus(Status.ONLINE);
            currentUser = user;
            return user;
        } catch (DAOException e) {
            throw new ServiceFileWriteException("Writing file caused an error.",e);
        }
    }

    @Override
    public void removeUser(int id) throws ServiceException {
        try {
            userDAO.removeUserById(id);
        } catch (DAOException e) {
            throw new ServiceRemoveBookException("Impossible to remove.",e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceEmptyDataException("Empty data.",e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        User user;
        try {
            user= userDAO.getUserById(id);
            return user;
        } catch (DAOException e) {
            throw new ServiceUserNotFoundException("User not found",e);
        }

    }

    public static boolean isAdmin(User user) {
        if (user != null && user.getRole() == Role.ADMIN) {
            return true;
        }
        return false;
    }

    public static boolean isOnline(User user) {
        if (user != null && user.getStatus() == Status.ONLINE) {
            return true;
        }
        return false;
    }
}
