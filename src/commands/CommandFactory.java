package commands;

public class CommandFactory {

    public ICommand generateCommand(CommandCode code) {
        ICommand command;

        switch (code) {
            case LogIn -> command = new LogInCommand();
            case LogOut -> command = new LogOutCommand();
            default -> command = null;
        }
        
        return command;
    }
}
