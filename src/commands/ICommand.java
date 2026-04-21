package commands;

public interface ICommand {

    boolean RequiresLogIn();

    boolean RequiresLogOut();

    boolean Execute(CommandContext context);
}
