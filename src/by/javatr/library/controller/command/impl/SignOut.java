package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.exception.service.ServiceException;
import by.javatr.library.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.util.Response;
import java.util.LinkedHashMap;

public class SignOut implements Command {

    Response response;

    @Override
    public Response checkParameters() {
        response=new Response();
        response.setStatus(false);
        response.addParameter("SIGN_OUT","true");
        return response;
    }

//todo
    @Override
    public Response execute(LinkedHashMap<String,String> request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.signOut();
            response=new Response();
            response.addParameter("message","Sign out is successful.");
            response.setStatus(true);

        } catch (ServiceException e) {
            response=new Response();
            response.addParameter("message","Error during sign out procedure");
            response.setStatus(false);
        }
        return response;
    }
}
