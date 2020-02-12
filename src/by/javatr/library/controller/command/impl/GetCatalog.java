package by.javatr.library.controller.command.impl;

import by.javatr.library.dao.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.LibraryService;
import by.javatr.library.dao.util.Response;
import by.javatr.library.dao.util.Role;

import java.util.List;
import java.util.Map;

public class GetCatalog implements Command {

    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static ClientService clientService = serviceFactory.getClientService();

    @Override
    public Response checkParameters() {
        Response response = new Response();
        response.setStatus(false);
        return response;
    }

    @Override
    public Response execute(Map<String, String> request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        Response response;

        try {
            response = new Response();
            List<Book> allBook = libraryService.getAllBook();
            String booksToString = transformListsOfBooksToString(allBook);

            response.addParameter("message", booksToString);
            response.setStatus(true);

        } catch (ServiceException e) {
            return getUnsuccessfulResponse("We wasn't able to show catalog now. Try again a little bit later.");
        }
        return response;
    }


    private String transformListsOfBooksToString(List<Book> books) {
        StringBuilder booksToString = new StringBuilder();
        booksToString.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (Book book : books) {
            if (clientService.getAuthorizedUser().getRole() == Role.ADMIN) {
                booksToString.append("id: ").append(book.getId()).append("\n");
            }
            booksToString.append("author name: ").append(book.getAuthorName()).append(" \n");
            booksToString.append("author surname: ").append(book.getAuthorSurname()).append(" \n");
            booksToString.append("book year: ").append(book.getYear()).append(" \n");
            booksToString.append("book name: ").append(book.getBookName()).append(" \n");
            booksToString.append("description: \n").append(book.getDescription()).append("\n");
            booksToString.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        return booksToString.toString();
    }
}
