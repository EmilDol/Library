package commands;

import container.Container;
import data.repositories.contracts.IBookRepository;
import data.repositories.contracts.IUserRepository;

public class SaveAsCommand implements ICommand{
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

        IUserRepository userRepository = Container.getInstance().getRepository(IUserRepository.class);
        if (!userRepository.SaveFile(filename))
            return false;

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        if (!bookRepository.SaveFile(filename))
            return false;

        return true;
    }
}
