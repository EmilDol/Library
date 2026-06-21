package commands;

import container.Container;
import data.repositories.contracts.BookRepository;
import data.repositories.contracts.UserRepository;

/**
 * Команда за запазване на данните от библиотеката в посочен файл.
 * @author Емил Долчинков
 */
public class SaveAsCommand implements Command {
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
     * Изпълнява командата за запазване на данни в нов файл.
     * @param context Контекстът на командата, съдържащ целевото име на файла.
     * @return true, ако данните са успешно запазени във файла, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String filename = context.get("filename", String.class);

        UserRepository userRepository = Container.getInstance().getRepository(UserRepository.class);
        if (!userRepository.SaveFile(filename))
            return false;

        BookRepository bookRepository = Container.getInstance().getRepository(BookRepository.class);
        if (!bookRepository.SaveFile(filename))
            return false;

        return true;
    }
}
