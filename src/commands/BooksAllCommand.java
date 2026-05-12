package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.List;

public class BooksAllCommand implements ICommand{
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
        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);
        List<Book> books = bookRepository.GetAll();

        for (Book book : books) {
            System.out.println(book);
        }

        return true;
    }
}
