package data.repositories;

import data.models.User;
import data.repositories.contracts.IUserRepository;

import java.util.List;

public class UserTxtFileRepository implements IUserRepository {


    @Override
    public User GetById(int id) {
        return null;
    }

    @Override
    public List<User> GetAll() {
        return List.of();
    }

    @Override
    public boolean Add(User record) {
        return false;
    }

    @Override
    public boolean Update(int id, User record) {
        return false;
    }

    @Override
    public boolean Remove(int id) {
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
}
