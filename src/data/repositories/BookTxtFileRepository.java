package data.repositories;

import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

public class BookTxtFileRepository implements IBookRepository {
    private List<Book> items;

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

    @Override
    public boolean SaveFile(String fileName) {
        return false;
    }

    @Override
    public boolean Load(String fileName) {
        return false;
    }
}
