package by.javatr.library.service.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.exception.*;
import by.javatr.library.dao.factory.DAOFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.validator.BookValidator;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();

    @Override
    public void addNewBook(String authorName, String authorSurname, String bookName, int year, String description) throws ServiceException {

        if(!BookValidator.validateAuthorName(authorName)&&
                !BookValidator.validateAuthorName(authorSurname)){
            throw new ServiceBookLogicException("Author name is incorrect.");
        }

        if(!BookValidator.validateBookName(bookName)){
            throw new ServiceBookLogicException("Book name is incorrect.");
        }

        if(!BookValidator.validateBookYear(year)){
            throw new ServiceBookLogicException("Book year is incorrect.");
        }

        if(!BookValidator.validateDescription(description)){
            throw new ServiceBookLogicException("Description is too large.");
        }

        Book book = new Book();
        book.setAuthorName(authorName);
        book.setAuthorSurname(authorSurname);
        book.setBookName(bookName);
        book.setYear(year);
        book.setDescription(description);

        try {
            bookDAO.save(book);
        } catch (DAOException ex) {
            throw new ServiceAddBookException("Writing file caused an error.",ex);
        }

    }

    @Override
    public List<Book> getAllBook() throws ServiceException {

        try {
            return bookDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceGetAllBookException("Impossible action",e);
        }
    }

    @Override
    public List<Book> getBookByAuthorSurname(String surname) throws ServiceException {
        List<Book> books;
        try {
            books=bookDAO.getBookByAuthorSurname(surname);
            if(books.size()==0){
              throw new ServiceEmptyDataException("Empty data");
            }

        } catch (DAOException ex) {
            throw new ServiceGetBookByAuthorSurnameException("Impossible to get data",ex);
        }
        return books;
    }

    @Override
    public void removeBook(int id) throws ServiceException {

        try {
            bookDAO.remove(id);
        } catch (DAOException e) {
            throw new ServiceRemoveBookException("Impossible to remove.",e);
        }
    }
}
