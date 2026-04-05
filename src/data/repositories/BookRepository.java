package data.repositories;

import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

public class BookRepository implements IBookRepository {
    @Override
    public Book GetById(int id) {
        return null;
    }

    @Override
    public List<Book> GetAll() {
        return List.of();
    }

    @Override
    public boolean Add(Book record) {
        return false;
    }

    @Override
    public boolean Update(int id, Book record) {
        return false;
    }

    @Override
    public boolean Remove(int id) {
        return false;
    }

    @Override
    public boolean Remove(Book record) {
        return false;
    }
}
