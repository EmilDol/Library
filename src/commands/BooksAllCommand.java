package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

/**
 * Команда за показване на всички книги в библиотеката.
 * @author Емил Долчинков
 */
public class BooksAllCommand implements ICommand{
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
     * Изпълнява командата за извеждане на всички книги.
     * @param context Контекстът на командата, съдържащ необходимите данни.
     * @return true, ако изпълнението е успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        List<Book> books = bookRepository.GetAll();

        for (Book book : books) {
            System.out.println(book);
        }

        return true;
    }
}
