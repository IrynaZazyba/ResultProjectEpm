package by.javatr.library.controller.command.impl;

import by.javatr.library.dao.bean.User;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.util.Response;

import java.util.List;
import java.util.Map;

public class GetUsers implements Command {


    @Override
    public Response checkParameters() {
        Response response = new Response();
        response.setStatus(false);
        return response;
    }

    @Override
    public Response execute(Map<String, String> request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        Response response;
        try {
            List<User> allUsers = clientService.getAllUsers();
            String usersToString = transformListOfUsersToString(allUsers);

            response = new Response();
            response.addParameter("message", usersToString);
            response.setStatus(true);

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("We wasn't able to show all users.");
        }
        return response;
    }


    private String transformListOfUsersToString(List<User> users) {
        StringBuilder usersToString = new StringBuilder();
        usersToString.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (User user : users) {
            usersToString.append("id: ").append(user.getId()).append("\n");
            usersToString.append("name: ").append(user.getName()).append(" \n");
            usersToString.append("login: ").append(user.getLogin()).append("\n");
            usersToString.append("year: ").append(user.getYear()).append("\n");
            usersToString.append("status: ").append(user.getStatus()).append("\n");
            usersToString.append("role: ").append(user.getRole()).append("\n");
            usersToString.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        return usersToString.toString();
    }

}
