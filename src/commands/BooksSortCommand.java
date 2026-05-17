package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

/**
 * Команда за сортиране на книги въз основа на свойство и посока.
 * @author Емил Долчинков
 */
public class BooksSortCommand implements ICommand{
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
     * Изпълнява командата за сортиране на книги.
     * @param context Контекстът на командата, съдържащ свойство и посока за сортиране.
     * @return true, ако изпълнението е успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String prop = context.get("prop", String.class);
        String direction = context.get("direction", String.class);

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        List<Book> books = bookRepository.GetAll();

        // имплементиране на сортирането по-късно

        return true;
    }
}
