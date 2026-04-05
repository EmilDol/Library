package data.repositories.contracts;

import java.util.List;

public interface IRepository<T> {
    T GetById(int id);

    List<T> GetAll();

    boolean Add(T record);

    boolean Update(int id, T record);

    boolean Remove(int id);

    boolean Remove(T record);
}
