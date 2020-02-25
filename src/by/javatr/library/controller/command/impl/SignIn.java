package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.User;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.util.Response;

import java.util.Map;

public class SignIn implements Command {


    @Override
    public Response checkParameters() {
        Response response = new Response();
        response.setStatus(true);
        response.addParameter("login", null);
        response.addParameter("password", null);
        return response;
    }

    @Override
    public Response execute(Map<String, String> parameters) {
        String login = parameters.get("login");
        String password = parameters.get("password");
        Response response=new Response();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            User user=clientService.signIn(login, password);
            response.setStatus(true);
            response.addParameter("message", "Welcome!");
            response.addParameter("userId",String.valueOf(user.getId()));

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("Error during login procedure");
        }
        return response;
    }

}
