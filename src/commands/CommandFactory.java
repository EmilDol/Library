package commands;

/**
 * Фабрѝчен клас за създаване на инстанции на команди въз основа на код на команда.
 * @author Емил Долчинков
 */
public class CommandFactory {

    /**
     * Създава и връща инстанция на команда, съответстваща на дадения код на команда.
     * @param code Кодът на командата, идентифициращ командата, която трябва да бъде създадена.
     * @return Инстанция на ICommand или null, ако кодът не е разпознат.
     */
    public static Command GetCommand(CommandCode code) {
        Command cmd = null;
        switch (code) {
            case LogIn -> {
                cmd = new LogInCommand();
                break;
            }
            case LogOut -> {
                cmd = new LogOutCommand();
                break;
            }
            case Open -> {
                cmd = new OpenCommand();
                break;
            }
            case Close -> {
                cmd = new CloseCommand();
                break;
            }
            case Save -> {
                cmd = new SaveCommand();
                break;
            }
            case SaveAs -> {
                cmd = new SaveAsCommand();
                break;
            }
            case Help -> {
                cmd = new HelpCommand();
                break;
            }
            case Exit -> {
                cmd = new ExitCommand();
                break;
            }
            case BooksAll -> {
                cmd = new BooksAllCommand();
                break;
            }
            case BooksFind -> {
                cmd = new BooksFindCommand();
                break;
            }
            case BooksSort -> {
                cmd = new BooksSortCommand();
                break;
            }
            case BooksAdd -> {
                cmd = new BooksAddCommand();
                break;
            }
            case BooksRemove -> {
                cmd = new BooksRemoveCommand();
                break;
            }
            case UsersAdd -> {
                cmd = new UsersAddCommand();
                break;
            }
            case UsersRemove -> {
                cmd = new UsersRemoveCommand();
                break;
            }
            // TODO: да добавя грешка за default
        }

        return cmd;
    }

}
