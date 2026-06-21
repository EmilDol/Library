package data.repositories.contracts;

import java.util.List;

/**
 * Общ интерфейс на хранилище (repository) за управление на данни от тип T.
 *
 * @param <T> Типът на обектите, управлявани от хранилището.
 * @author Емил Долчинков
 */
public interface Repository<T> {
    /**
     * Извлича всички записи от хранилището.
     *
     * @return Списък с всички записи.
     */
    List<T> GetAll();

    /**
     * Извлича запис по неговото име.
     *
     * @param name Името на записа, който трябва да бъде намерен.
     * @return Записът, ако е намерен, в противен случай null.
     */
    T GetByName(String name);

    /**
     * Добавя нов запис в хранилището.
     *
     * @param record Записът, който да бъде добавен.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Add(T record);

    /**
     * Актуализира съществуващ запис в хранилището.
     *
     * @param record Записът, който да бъде актуализиран.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Update(T record);

    /**
     * Премахва запис от хранилището.
     *
     * @param record Записът, който да бъде премахнат.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Remove(T record);

    /**
     * Премахва запис от хранилището по неговото ID.
     *
     * @param id ID-то на записа, който да бъде премахнат.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Remove(Integer id);

    /**
     * Записва данните от хранилището във файл.
     *
     * @param fileName Името на файла.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean SaveFile(String fileName);

    /**
     * Зарежда данните на хранилището от файл.
     *
     * @param fileName Името на файла.
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Load(String fileName);

    /**
     * Изчиства всички записи от хранилището.
     *
     * @return true, ако е успешно, в противен случай false.
     */
    boolean Clear();

    /**
     * Извлича запис по неговия уникален идентификатор.
     *
     * @param id ID-то на записа, който трябва да бъде намерен.
     * @return Записът, ако е намерен, в противен случай null.
     */
    T GetById(int id);
}
