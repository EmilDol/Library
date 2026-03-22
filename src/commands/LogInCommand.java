package commands;

public class LogInCommand implements ICommand{

    @Override
    public boolean RequiresLogIn() {
        return false;
    }

    @Override
    public boolean RequiresLogOut() {
        return true;
    }

    @Override
    public void Execute() {

    }
}
