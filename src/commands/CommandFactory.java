package commands;

public class CommandFactory {

    static ICommand GetCommand(CommandCode code) {
        ICommand cmd = null;
        switch (code) {
            case LogIn -> {
                cmd = new LogInCommand();
            }
            case LogOut -> {
                cmd = new LogOutCommand();
            }
            // TODO: да добавя грешка за default
        }

        return cmd;
    }

}
