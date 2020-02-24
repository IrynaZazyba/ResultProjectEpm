package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.util.Response;
import by.javatr.library.controller.command.CommandName;
import java.util.Map;

public class RemoveUser implements Command {


    @Override
    public Response checkParameters() {
        Response response =new Response();
        response.addParameter("id", null);
        response.setCommandName(CommandName.GET_USERS.toString());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response execute(Map<String,String> request){
        int id=0;
        Response response;
        try {
            id = Integer.parseInt(request.get("id"));
        } catch (NumberFormatException ex) {
            return getUnsuccessfulResponse("We wasn't able to remove user with such id.");
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.removeUser(id);
            response=new Response();
            response.addParameter("message","User was removed.");
            response.setStatus(true);

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("We wasn't able to remove user.");
        }
        return response;
    }

  }
