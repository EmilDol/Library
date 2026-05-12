package commands;

public class ExitCommand implements ICommand{
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
        System.exit(0);
        return true;
    }
}
