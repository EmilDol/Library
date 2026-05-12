package commands;

import container.Container;
import context.Session;
import data.models.User;
import data.repositories.contracts.IUserRepository;

public class LogInCommand implements ICommand{

    @Override
    public boolean RequiresLogIn() {
        return false;
    }

    @Override
    public boolean RequiresLogOut() {
        return true;
    }

    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    @Override
    public boolean Execute(CommandContext context) {
        String username = context.get("username", String.class);
        String password = context.get("password", String.class);

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);
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
