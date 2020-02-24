package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.util.Response;
import by.javatr.library.controller.command.CommandName;

import java.util.Map;

public class RemoveBook implements Command {



    @Override
    public Response checkParameters() {
        Response response = new Response();
        response.addParameter("id", null);
        response.setCommandName(CommandName.GET_CATALOG.toString());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response execute(Map<String, String> request) {
        int id=0;
        Response response;
        try {
            id = Integer.parseInt(request.get("id"));
        } catch (NumberFormatException ex) {
            return getUnsuccessfulResponse("We wasn't able to remove book with such id.");
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            response = new Response();
            libraryService.removeBook(id);
            response.addParameter("message", "Book is removed.");
            response.setStatus(true);

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("We wasn't able to remove book.");
        }
        return response;
    }

}
