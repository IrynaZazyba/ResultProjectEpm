package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.util.Response;

import java.util.Map;

public class AddBook implements Command {



    @Override
    public Response checkParameters() {
        Response response = new Response();
        response.setStatus(true);
        response.addParameter("bookName", null);
        response.addParameter("authorName", null);
        response.addParameter("authorSurname", null);
        response.addParameter("year", null);
        response.addParameter("description", null);
        response.setStatus(true);
        return response;
    }

    @Override
    public Response execute(Map<String, String> request) {
        String bookName = request.get("bookName");
        String authorName = request.get("authorName");
        String authorSurname = request.get("authorSurname");

        int year = 0;
        Response response;
        try {
            year = Integer.parseInt(request.get("year"));
        } catch (NumberFormatException ex) {
            return getUnsuccessfulResponse("Year is invalid.");
        }

        String description = request.get("description");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            libraryService.addNewBook(authorName, authorSurname, bookName, year, description);
            response = new Response();
            response.addParameter("message", "Book was added!");
            response.setStatus(true);

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("We wasn't able to add book.");
        }
        return response;
    }



}
