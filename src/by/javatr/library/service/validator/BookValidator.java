package by.javatr.library.service.validator;

import by.javatr.library.bean.Book;

public class BookValidator {

    public static boolean validateBookYear(int year) {

        String s = String.valueOf(year);
        return s.matches("\\d{1,4}$");
    }


    public static boolean validateAuthorName(String name) {
        return name.matches("([a-zA-Z-]|[а-яА-Я-]){2,25}([\\s]([a-zA-Z-]|[а-яА-Я-]){1,25}){0,3}$");
    }


    public static boolean validateBookName(String bookName) {

        return  bookName.matches("([А-Яа-я0-9_.-]|[A-Za-z0-9_.-]){1,75}.{1,45}");
    }


    public static boolean validateDescription(String name){

        return name.matches("([А-Яа-я0-9_.-]|[A-Za-z0-9_.-]){1,1000}.{1,1000}");
    }

    public static boolean bookValidator(Book book){
        return validateBookYear(book.getYear())||validateBookName(book.getBookName())
                ||validateAuthorName(book.getAuthorName())||validateAuthorName(book.getAuthorSurname())
                ||validateDescription(book.getDescription());
    }
}
