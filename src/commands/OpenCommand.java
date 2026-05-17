package commands;

import container.Container;
import data.models.User;
import data.repositories.contracts.IBookRepository;
import data.repositories.contracts.IUserRepository;

public class OpenCommand implements ICommand{
    @Override
    public boolean RequiresLogIn() {
        return false;
    }

    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    @Override
    public boolean Execute(CommandContext context) {
        String filename = context.get("filename", String.class);

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        if (!bookRepository.Load(filename))
            return false;

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);
        if (!userRepository.Load(filename))
            return false;

        User defaultAdmin = new User("admin", "i<3Java", true);

        if (userRepository.GetByName(defaultAdmin.getUsername()) == null && !userRepository.Add(defaultAdmin))
            return false;

        Container.getInstance().setLoadedFile(true, filename);

        return true;
    }
}
