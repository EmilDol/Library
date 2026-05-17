package commands;

/**
 * Команда за изход от приложението.
 *
 * @author Емил Долчинков
 */
public class ExitCommand implements ICommand{
    /**
     * Определя дали командата изисква потребителят да е влязъл в системата.
     *
     * @return false.
     */
    @Override
    public boolean RequiresLogIn() {
        return false;
    }

    /**
     * Определя дали командата изисква потребителят да е излязъл от системата.
     *
     * @return false.
     */
    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    /**
     * Определя дали командата изисква администраторски привилегии.
     *
     * @return false.
     */
    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    /**
     * Изпълнява командата за изход, извеждайки прощално съобщение.
     *
     * @param context Контекстът на командата.
     * @return true, ако изпълнението е успешно.
     */
    @Override
    public boolean Execute(CommandContext context) {
        System.out.println("Goodbye!");
        return true;
    }
}
