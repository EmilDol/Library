package commands;

import container.Container;
import data.repositories.contracts.BookRepository;
import data.repositories.contracts.UserRepository;

/**
 * Команда за затваряне на текущия файл и изчистване на хранилищата.
 * @author Емил Долчинков
 */
public class CloseCommand implements Command {
    /**
     * Проверява дали командата изисква потребителят да е влязъл в системата.
     * @return true, ако се изисква влизане, в противен случай false.
     */
    @Override
    public boolean RequiresLogIn() {
        return false;
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
     * Изпълнява командата за затваряне на файла и изчистване на данните.
     * @param context Контекстът на командата.
     * @return true, ако операцията е успешна, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        BookRepository bookRepository = Container.getInstance().getRepository(BookRepository.class);
        if (!bookRepository.Clear())
            return false;

        UserRepository userRepository = Container.getInstance().getRepository(UserRepository.class);
        if (!userRepository.Clear())
            return false;

        Container.getInstance().unloadFile();

        return true;
    }
}
