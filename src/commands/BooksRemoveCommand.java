package commands;

import container.Container;
import data.repositories.contracts.BookRepository;

/**
 * Команда за премахване на книга от библиотеката по нейния ID.
 * Тази команда изисква потребителят да е влязъл като администратор.
 *
 * @author Емил Долчинков
 */
public class BooksRemoveCommand implements Command {
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
     * Изпълнява командата за премахване на книга, използвайки предоставения контекст.
     *
     * @param context Контекстът на командата, съдържащ ID на книгата.
     * @return true, ако книгата е премахната успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        Integer id = context.get("id", Integer.class);

        BookRepository bookRepository = Container.getInstance().getRepository(BookRepository.class);

        if (!bookRepository.Remove(id))
            return false;

        return true;
    }
}
