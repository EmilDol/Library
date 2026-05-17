package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Команда за добавяне на нова книга към библиотеката.
 * Тази команда изисква потребителят да е влязъл като администратор.
 *
 * @author Емил Долчинков
 */
public class BooksAddCommand implements ICommand{
    /**
     * Указва дали тази команда изисква потребителят да е влязъл в системата.
     *
     * @return true, тъй като се изисква влизане.
     */
    @Override
    public boolean RequiresLogIn() {
        return true;
    }

    /**
     * Указва дали тази команда изисква потребителят да е излязъл от системата.
     *
     * @return false, тъй като не се изисква излизане.
     */
    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    /**
     * Указва дали тази команда изисква администраторски привилегии.
     *
     * @return true, тъй като се изискват администраторски привилегии.
     */
    @Override
    public boolean RequiresAdmin() {
        return true;
    }

    /**
     * Изпълнява командата за добавяне на нова книга, използвайки предоставения контекст.
     *
     * @param context Контекстът на командата, съдържащ подробности за книгата.
     * @return true, ако книгата е добавена успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String bookname = context.get("bookname", String.class);
        String author = context.get("author", String.class);
        String genre = context.get("genre", String.class);
        String description = context.get("description", String.class);
        Integer year = context.get("year", Integer.class);
        Double rating = context.get("rating", Double.class);
        String keywordsInput = context.get("keywords", String.class);

        List<String> keywords = Arrays.asList(keywordsInput.split(","));

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);

        Integer id = bookRepository.GetAll().stream()
                .map(Book::getId)
                .max(Integer::compareTo)
                .orElse(0);

        id++;

        Book book = new Book(author, bookname, genre, description, year, keywords, rating, id);

        if (!bookRepository.Add(book))
            return false;

        return true;
    }
}
