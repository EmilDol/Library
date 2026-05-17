package commands;

/**
 * Команда за показване на списъка с налични команди.
 *
 * @author Емил Долчинков
 */
public class HelpCommand implements ICommand{
    /**
     * Определя дали командата изисква потребителят да е влязъл в системата.
     *
     * @return false, тъй като помощта е достъпна за всички.
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
     * Изпълнява командата за помощ, показвайки всички налични команди.
     *
     * @param context Контекстът на командата.
     * @return true, ако изпълнението е успешно.
     */
    @Override
    public boolean Execute(CommandContext context) {
        String help = """
Available commands:
open
close
save
save as
help
exit
login
logout
books all
books info
books find
books sort
users add
users remove""";

        System.out.println(help);

        return true;
    }
}
