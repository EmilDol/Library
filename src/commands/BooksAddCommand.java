package commands;

import container.Container;
import data.models.Book;
import data.repositories.contracts.IBookRepository;

import java.util.Arrays;
import java.util.List;

public class BooksAddCommand implements ICommand{
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
        String bookname = context.get("bookname", String.class);
        String author = context.get("author", String.class);
        String genre = context.get("genre", String.class);
        String description = context.get("description", String.class);
        Integer year = context.get("year", Integer.class);
        Double rating = context.get("rating", Double.class);
        String keywordsInput = context.get("keywords", String.class);

        List<String> keywords = Arrays.asList(keywordsInput.split(","));

        IBookRepository bookRepository = Container.getInstance().getRepository(IBookRepository.class);

        Integer id = bookRepository.GetAll().stream()
                .map(Book::getId)
                .max(Integer::compareTo)
                .orElse(0);

        id++;

        Book book = new Book(author, bookname, genre, description, year, keywords, rating, id);

        if (!bookRepository.Add(book))
            return false;

        return true;
    }
}
