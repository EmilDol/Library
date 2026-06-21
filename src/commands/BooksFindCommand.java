package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.BookRepository;

/**
 * Команда за намиране на книга по нейното име.
 * @author Емил Долчинков
 */
public class BooksFindCommand implements Command {
    /**
     * Проверява дали командата изисква потребителят да е влязъл в системата.
     * @return true, ако се изисква влизане, в противен случай false.
     */
    @Override
    public boolean RequiresLogIn() {
        return true;
    }

    /**
     * Проверява дали командата изисква потребителят да е излязъл от системата.
     * @return true, ако се изисква излизане, в противен случай false.
     */
    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    /**
     * Проверява дали командата изисква администраторски привилегии.
     * @return true, ако се изискват администраторски привилегии, в противен случай false.
     */
    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    /**
     * Изпълнява командата за намиране на книга по име.
     * @param context Контекстът на командата, съдържащ стойността за търсене.
     * @return true, ако книгата е намерена и изведена, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String value = context.get("value", String.class);

        BookRepository bookRepository = Container.getInstance().getRepository(BookRepository.class);
        Book book = bookRepository.GetByName(value);
        if (book == null)
            return false;

        System.out.println(book);

        return true;
    }
}
