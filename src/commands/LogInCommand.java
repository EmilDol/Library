package commands;

import container.Container;
import context.Session;
import data.models.User;
import data.repositories.contracts.UserRepository;

/**
 * Команда за влизане на потребител в системата.
 *
 * @author Емил Долчинков
 */
public class LogInCommand implements Command {

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
     * @return true.
     */
    @Override
    public boolean RequiresLogOut() {
        return true;
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
     * Изпълнява командата за влизане, като удостоверява потребителя.
     *
     * @param context Контекстът на командата, съдържащ потребителско име и парола.
     * @return true, ако влизането е успешно, в противен случай false.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String username = context.get("username", String.class);
        String password = context.get("password", String.class);

        UserRepository userRepository = Container.getInstance().getRepository(UserRepository.class);
        User user = userRepository.GetByName(username);
        if (user == null)
        {
            return false;
        }

        if (!user.getPassword().equals(password))
        {
            return false;
        }

        Session session = Container.getInstance().getSession();

        session.setUser(user);

        return true;
    }
}
