package data.repositories;

import data.models.User;
import data.repositories.contracts.IUserRepository;

import java.util.List;

public class UserTxtFileRepository implements IUserRepository {
    private static List<User> items;

    @Override
    public List<User> GetAll() {
        return List.of();
    }

    @Override
    public User GetByName(String name) {
        return null;
    }

    @Override
    public boolean Add(User record) {
        return false;
    }

    @Override
    public boolean Update(User record) {
        return false;
    }

    @Override
    public boolean Remove(User record) {
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
    public User GetById(int id) {
        return null;
    }
}
