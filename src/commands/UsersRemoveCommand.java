package commands;

import container.Container;
import data.models.User;
import data.repositories.contracts.UserRepository;

/**
 * Команда за премахване на потребител от библиотечната система.
 * Тази команда изисква потребителят да е влязъл като администратор.
 *
 * @author Емил Долчинков
 */
public class UsersRemoveCommand implements Command {
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
     * Изпълнява командата за премахване на потребител, използвайки предоставения контекст.
     *
     * @param context Контекстът на командата, съдържащ потребителското име.
     * @return true, ако потребителят е премахнат успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String username = context.get("username", String.class);

        UserRepository userRepository = Container.getInstance().getRepository(UserRepository.class);
        if (userRepository.GetByName(username) != null)
            return false;

        if (Container.getInstance().getSession().getUser().getUsername().equals(username))
            return false;

        String password = context.get("password", String.class);
        User user = new User(username, password, false);

        if (!userRepository.Add(user))
            return false;

        return true;
    }
}
