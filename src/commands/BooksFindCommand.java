package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

public class BooksFindCommand implements ICommand{
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
        return false;
    }

    @Override
    public boolean Execute(CommandContext context) {
        String value = context.get("value", String.class);

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        Book book = bookRepository.GetByName(value);
        if (book == null)
            return false;

        System.out.println(book);

        return true;
    }
}
