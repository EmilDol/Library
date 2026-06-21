package commands;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Фабричен клас за създаване на инстанции на команди въз основа на код на команда.
 * @author Емил Долчинков
 */
public class CommandFactory {

    private static final Map<CommandCode, Supplier<Command>> COMMAND_MAP = new EnumMap<>(CommandCode.class);

    static {
        COMMAND_MAP.put(CommandCode.LOG_IN, LogInCommand::new);
        COMMAND_MAP.put(CommandCode.LOG_OUT, LogOutCommand::new);
        COMMAND_MAP.put(CommandCode.OPEN, OpenCommand::new);
        COMMAND_MAP.put(CommandCode.CLOSE, CloseCommand::new);
        COMMAND_MAP.put(CommandCode.SAVE, SaveCommand::new);
        COMMAND_MAP.put(CommandCode.SAVE_AS, SaveAsCommand::new);
        COMMAND_MAP.put(CommandCode.HELP, HelpCommand::new);
        COMMAND_MAP.put(CommandCode.EXIT, ExitCommand::new);

        COMMAND_MAP.put(CommandCode.BOOKS_ALL, BooksAllCommand::new);
        COMMAND_MAP.put(CommandCode.BOOKS_FIND, BooksFindCommand::new);
        COMMAND_MAP.put(CommandCode.BOOKS_SORT, BooksSortCommand::new);
        COMMAND_MAP.put(CommandCode.BOOKS_ADD, BooksAddCommand::new);
        COMMAND_MAP.put(CommandCode.BOOKS_REMOVE, BooksRemoveCommand::new);

        COMMAND_MAP.put(CommandCode.USERS_ADD, UsersAddCommand::new);
        COMMAND_MAP.put(CommandCode.USERS_REMOVE, UsersRemoveCommand::new);
    }

    /**
     * Създава и връща инстанция на команда, съответстваща на дадения код на команда.
     * @param code Кодът на командата, идентифициращ командата, която трябва да бъде създадена.
     * @return Инстанция на ICommand или null, ако кодът не е разпознат.
     */
    public static Command getCommand(CommandCode code) {
        Supplier<Command> supplier = COMMAND_MAP.get(code);

        if (supplier == null) {
            throw new IllegalArgumentException("Unsupported command: " + code);
        }

        return supplier.get();
    }

}
