package commands;

import container.Container;
import data.repositories.contracts.IBookRepository;
import data.repositories.contracts.IUserRepository;

/**
 * Команда за запазване на данните от библиотеката в текущо заредения файл.
 * @author Емил Долчинков
 */
public class SaveCommand implements ICommand{
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
     * Изпълнява командата за запазване на данни в текущия файл.
     * @param context Контекстът на командата.
     * @return true, ако данните са успешно запазени, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String filename = Container.getInstance().getLoadedFileName();

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);
        if (!userRepository.SaveFile(filename))
            return false;

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        if (!bookRepository.SaveFile(filename))
            return false;

        return true;
    }
}
