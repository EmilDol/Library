package commands;

import container.Container;
import data.models.User;
import data.repositories.contracts.IUserRepository;

public class UsersRemoveCommand implements ICommand {
    @Override
    public boolean RequiresLogIn() {
        return true;
    }

    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    @Override
    public boolean RequiresAdmin() {
        return true;
    }

    @Override
    public boolean Execute(CommandContext context) {
        String username = context.get("username", String.class);

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);
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
