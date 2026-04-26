package commands;

public interface ICommand {

    boolean RequiresLogIn();

    boolean RequiresLogOut();

    boolean RequiresAdmin();

    boolean Execute(CommandContext context);
}
