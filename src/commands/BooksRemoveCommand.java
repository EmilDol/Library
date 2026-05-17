package commands;

import container.Container;
import data.repositories.contracts.IBookRepository;

public class BooksRemoveCommand implements ICommand{
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
        Integer id = context.get("id", Integer.class);

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);

        if (!bookRepository.Remove(id))
            return false;

        return true;
    }
}
