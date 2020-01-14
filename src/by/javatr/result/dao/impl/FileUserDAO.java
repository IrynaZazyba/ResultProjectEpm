package by.javatr.result.dao.impl;

import by.javatr.result.util.Status;
import by.javatr.result.bean.User;
import by.javatr.result.dao.UserDAO;
import by.javatr.result.exception.*;
import by.javatr.result.exception.dao.*;
import by.javatr.result.parser.ReadFileManager;
import by.javatr.result.parser.WriteFileManager;
import by.javatr.result.validator.UserValidator;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileUserDAO implements UserDAO {

    private final static File FILE = new File("user.jsonl");


    public User getUserByLogin(String login) throws DAOException {
        List<User> users = getAll();
        if (users == null) {
            throw new DAOUserNotFoundException("User not found");
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        throw new DAOUserNotFoundException("User not found");
    }


    public void removeUserById(int id) throws DAOException {

        List<User> users = getAll();
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); )
            if (iterator.next().getId() == id) {
                iterator.remove();
            }

        try {
            WriteFileManager.writeToFile(users, FILE);

        } catch (FileParserException e) {
            throw new DAOFileParserException("Writing file caused an error.");
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Gson gson = new Gson();
        String s = null;

        List<String> usersString;
        try {
            usersString = ReadFileManager.readFile(FILE);
            if (usersString == null) {
                throw new DAOEmptyDataException("Empty data.");
            }
            for (String user : usersString) {
                users.add(gson.fromJson(user, User.class));
            }
        } catch (ReadFileException e) {
            throw new DAOFileParserException("File not found.");
        }

        return users;
    }


    @Override
    public void save(User user) throws DAOException {

        if (!UserValidator.validateYear(user.getYear())) {
            throw new DAOUserLogicException("Year is incorrect.");
        }

        if (!UserValidator.validateLogin(user.getLogin())) {
            throw new DAOUserLogicException("Login is incorrect.");
        }

        if (!UserValidator.validatePassword(user.getPassword())) {
            throw new DAOUserLogicException("Password is incorrect.");
        }

        if (!UserValidator.validateName(user.getName())) {
            throw new DAOUserLogicException("Name is incorrect.");
        }
        long id = Math.abs(user.hashCode()) + (System.currentTimeMillis() / 10000);

        while (checkExistUserId(id)) {
            id = Math.abs(user.hashCode()) + (System.currentTimeMillis() / 10000);
        }
        user.setId(id);
        user.setStatus(Status.OFFLINE);

        try {
            WriteFileManager.writeToFile(user, FILE, true);

        } catch (FileParserException e) {
            throw new DAOFileParserException("Writing file caused an error.");
        }
    }

    private boolean checkExistUserId(long id) throws DAOException {
        List<User> users = getAll();
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

}

