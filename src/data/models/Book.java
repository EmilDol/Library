package data.models;

import java.util.List;

/**
 * Представлява книга в библиотечната система.
 *
 * @author Емил Долчинков
 */
public class Book {

    private String author;
    private String title;
    private String genre;
    private String description;
    private int year;
    private List<String> keywords;
    private double rating;
    private int id;

    /**
     * Създава книга с всички нейни детайли.
     *
     * @param author      Авторът на книгата.
     * @param title       Заглавието на книгата.
     * @param genre       Жанрът на книгата.
     * @param description Кратко описание на книгата.
     * @param year        Годината на издаване.
     * @param keywords    Списък с ключови думи, свързани с книгата.
     * @param rating      Рейтингът на книгата.
     * @param id          Уникалният идентификатор (или ISBN) на книгата.
     */
    public Book(String author, String title, String genre, String description,
                int year, List<String> keywords, double rating, int id) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = keywords;
        this.rating = rating;
        this.id = id;
    }

    /**
     * Конструктор по подразбиране за книга.
     */
    public Book() {
    }

    // Гетъри и сетъри

    /**
     * Връща автора на книгата.
     *
     * @return Авторът.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Задава автора на книгата.
     *
     * @param author Авторът, който да бъде зададен.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Връща заглавието на книгата.
     *
     * @return Заглавието.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Задава заглавието на книгата.
     *
     * @param title Заглавието, което да бъде зададено.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Връща жанра на книгата.
     *
     * @return Жанрът.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Задава жанра на книгата.
     *
     * @param genre Жанрът, който да бъде зададен.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Връща описанието на книгата.
     *
     * @return Описанието.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Задава описанието на книгата.
     *
     * @param description Описанието, което да бъде зададено.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Връща годината на издаване на книгата.
     *
     * @return Годината.
     */
    public int getYear() {
        return year;
    }

    /**
     * Задава годината на издаване на книгата.
     *
     * @param year Годината, която да бъде зададена.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Връща списъка с ключови думи за книгата.
     *
     * @return Списъкът с ключови думи.
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * Задава списъка с ключови думи за книгата.
     *
     * @param keywords Списъкът с ключови думи, който да бъде зададен.
     */
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Връща рейтинга на книгата.
     *
     * @return Рейтингът.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Задава рейтинга на книгата.
     *
     * @param rating Рейтингът, който да бъде зададен.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Връща уникалния идентификатор (или ISBN) на книгата.
     *
     * @return ID-то.
     */
    public int getId() {
        return id;
    }

    /**
     * Задава уникалния идентификатор (или ISBN) на книгата.
     *
     * @param id ID-то, което да бъде зададено.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Връща текстово представяне на книгата.
     *
     * @return Стринг, съдържащ детайли за книгата.
     */
    @Override
    public String toString() {
        return "author= " + author + ", title='" + title + ", genre= " + genre + ", isbn= " + id;
    }
}