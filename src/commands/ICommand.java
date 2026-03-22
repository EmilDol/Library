package commands;

public interface ICommand {

    boolean RequiresLogIn();

    boolean RequiresLogOut();

    void Execute();
}
