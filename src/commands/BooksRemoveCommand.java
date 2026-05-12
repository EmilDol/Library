package commands;

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
        return false;
    }
}
