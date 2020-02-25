package by.javatr.library.bean;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 2391836306763433659L;
    private long id;
    private String bookName;
    private String authorName;
    private String authorSurname;
    private int year;
    private String description;


    public Book() {}

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getBookName() {
        return bookName;
    }

    public final void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public final String getAuthorName() {
        return authorName;
    }

    public final void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public final String getAuthorSurname() {
        return authorSurname;
    }

    public final void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public final int getYear() {
        return year;
    }

    public final void setYear(int year) {
        this.year = year;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        if (bookName == null) {
            if (other.bookName != null) return false;
        } else {
            if (!bookName.equals(other.bookName)) return false;
        }
        if (authorName == null) {
            if (other.authorName != null) return false;
        } else{
            if(!authorName.equals(other.authorName)) return false;
        }
        if (authorSurname == null) {
            if (other.authorSurname != null) return false;
        } else{
            if(!authorSurname.equals(other.authorSurname)) return false;
        }
        if(year!=other.year) return false;
        if (description == null) {
            if(other.description != null) return false;
        } else{
            if(!description.equals(other.description)) return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + ((bookName == null) ? 0 : bookName.hashCode());
        result = result * prime + ((authorName == null) ? 0 : authorName.hashCode());
        result = result * prime + ((authorSurname == null) ? 0 : authorSurname.hashCode());
        result = result * prime + year;
        result = result * prime + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@' +
                "id=" + id +
                ", bookName='" + bookName +
                ", authorName='" + authorName +
                ", authorSurname='" + authorSurname +
                ", year=" + year +
                ", description='" + description;
    }
}
