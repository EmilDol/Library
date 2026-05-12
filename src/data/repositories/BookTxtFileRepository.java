package data.repositories;

import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

public class BookTxtFileRepository implements IBookRepository {
    private static List<Book> items;

    @Override
    public List<Book> GetAll() {
        return List.of();
    }

    @Override
    public Book GetByName(String name) {
        return null;
    }

    @Override
    public boolean Add(Book record) {
        return false;
    }

    @Override
    public boolean Update(Book record) {
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

    @Override
    public boolean Clear() {
        return false;
    }

    @Override
    public Book GetById(int id) {
        return null;
    }
}
