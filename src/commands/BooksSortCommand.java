package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

public class BooksSortCommand implements ICommand{
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
        String prop = context.get("prop", String.class);
        String direction = context.get("direction", String.class);

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        List<Book> books = bookRepository.GetAll();

        // implement sort later on

        return true;
    }
}
