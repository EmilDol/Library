package data.repositories.contracts;

import java.util.List;

public interface IRepository<T> {
    List<T> GetAll();

    T GetByName(String name);

    boolean Add(T record);

    boolean Update(T record);

    boolean Remove(T record);

    boolean Remove(Integer id);

    boolean SaveFile(String fileName);

    boolean Load(String fileName);

    boolean Clear();

    T GetById(int id);
}
