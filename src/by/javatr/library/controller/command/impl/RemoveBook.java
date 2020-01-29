package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.exception.service.ServiceException;
import by.javatr.library.factory.ServiceFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.util.Response;
import by.javatr.library.controller.command.CommandName;

import java.util.Map;

public class RemoveBook implements Command {

    Response response;

    @Override
    public Response checkParameters() {
        response = new Response();
        response.addParameter("id", null);
        response.setCommandName(CommandName.GET_CATALOG.toString());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response execute(Map<String, String> request) {
        int id=0;
        response = new Response();
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
