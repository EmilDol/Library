package commands;

import container.Container;
import data.models.User;
import data.repositories.contracts.BookRepository;
import data.repositories.contracts.UserRepository;

/**
 * Команда за отваряне на библиотечен файл и зареждане на данните от него.
 *
 * @author Емил Долчинков
 */
public class OpenCommand implements Command {
    /**
     * Определя дали командата изисква потребителят да е влязъл в системата.
     *
     * @return false.
     */
    @Override
    public boolean RequiresLogIn() {
        return false;
    }

    /**
     * Определя дали командата изисква потребителят да е излязъл от системата.
     *
     * @return false.
     */
    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    /**
     * Определя дали командата изисква администраторски привилегии.
     *
     * @return false.
     */
    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    /**
     * Изпълнява командата за отваряне, като зарежда посочения файл.
     *
     * @param context Контекстът на командата, съдържащ името на файла.
     * @return true, ако файлът е зареден успешно.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String filename = context.get("filename", String.class);

        BookRepository bookRepository = Container.getInstance().getRepository(BookRepository.class);
        if (!bookRepository.Load(filename))
            return false;

        UserRepository userRepository = Container.getInstance().getRepository(UserRepository.class);
        if (!userRepository.Load(filename))
            return false;

        User defaultAdmin = new User("admin", "i<3Java", true);

        if (userRepository.GetByName(defaultAdmin.getUsername()) == null && !userRepository.Add(defaultAdmin))
            return false;

        Container.getInstance().setLoadedFile(true, filename);

        return true;
    }
}
