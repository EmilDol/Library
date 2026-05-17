package commands;

import container.Container;
import data.models.User;
import data.repositories.contracts.IUserRepository;

public class UsersAddCommand implements ICommand{
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
        String password = context.get("password", String.class);

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);

        User tempUser = userRepository.GetByName(username);
        if (tempUser != null)
            return false;

        User user = new User(username, password, false);

        if (!userRepository.Add(user))
            return false;

        return true;
    }
}
