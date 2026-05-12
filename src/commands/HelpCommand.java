package commands;

public class HelpCommand implements ICommand{
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
        String help = "Available commands:" +
                "open" +
                "close" +
                "save" +
                "save as" +
                "help" +
                "exit" +
                "login" +
                "logout" +
                "books all" +
                "books info" +
                "books find" +
                "books sort" +
                "users add" +
                "users remove";

        System.out.println(help);

        return true;
    }
}
