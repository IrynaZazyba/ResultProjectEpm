package by.javatr.library.dao.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.exception.DAOBookLogicException;
import by.javatr.library.dao.exception.DAOEmptyDataException;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.dao.parser.exception.WriteFileException;
import by.javatr.library.dao.parser.exception.ReadFileException;
import by.javatr.library.dao.exception.DAOFileParserException;
import by.javatr.library.dao.parser.ReadFileManager;
import by.javatr.library.dao.parser.WriteFileManager;
import by.javatr.library.service.validator.BookValidator;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileBookDAO implements BookDAO {

    private static final String FILE_BOOK_PATH="resources/library.jsonl";
    private final File FILE = new File(FILE_BOOK_PATH);

    public List<Book> getBookByAuthorSurname(String authorSurname) throws DAOException {

        List<Book> books = getAll();
        if (books == null) {
            throw new DAOEmptyDataException("Book data is empty.");
        }
        List<Book> booksByAuthorSurname = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorSurname().equalsIgnoreCase(authorSurname)) {
                booksByAuthorSurname.add(book);
            }
        }
        return booksByAuthorSurname;
    }


    @Override
    public List<Book> getAll() throws DAOException {

        List<Book> books = new ArrayList<>();
        Gson gson = new Gson();
        String s = null;

        try {
            List<String> booksString = ReadFileManager.readFile(FILE);

            if (booksString == null) {
                throw new DAOEmptyDataException("Book data is empty.");
            }
            for (String book : booksString) {
                books.add(gson.fromJson(book, Book.class));
            }
        } catch (ReadFileException ex) {
            throw new DAOFileParserException("Reading file caused an error.",ex);
        }

        return books;
    }


    @Override
    public void save(Book book) throws DAOException {

        if (!BookValidator.bookValidator(book)) {
            throw new DAOBookLogicException("Book has incorrect field");
        }
        long id = Math.abs(book.hashCode()) + (System.currentTimeMillis() / 10000);

        while (checkExistUserId(id)) {
            id = Math.abs(book.hashCode()) + (System.currentTimeMillis() / 10000);
        }

        book.setId(id);

        try {
            WriteFileManager.writeToFile(book, FILE, true);
        } catch (WriteFileException ex) {
            throw new DAOFileParserException("Writing file caused an error",ex);
        }
    }


    @Override
    public void remove(int id) throws DAOException {

        List<Book> books = getAll();

        if (books != null) {
            Iterator<Book> bookIterator = books.iterator();
            while (bookIterator.hasNext()) {

                if (bookIterator.next().getId() == id) {
                    bookIterator.remove();
                }
            }
            try {
                WriteFileManager.writeToFile(books, FILE);
            } catch (WriteFileException ex) {
                throw new DAOFileParserException("Writing file caused an error",ex);
            }
        }
    }

    private boolean checkExistUserId(long id) throws DAOException {
        List<Book> books = getAll();
        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
