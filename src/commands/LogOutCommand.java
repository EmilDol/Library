package commands;

import container.Container;

/**
 * Команда за излизане на текущия потребител от сесията.
 * @author Емил Долчинков
 */
public class LogOutCommand implements ICommand {
    /**
     * Проверява дали командата изисква потребителят да е влязъл в системата.
     * @return true, ако се изисква влизане, в противен случай false.
     */
    @Override
    public boolean RequiresLogIn() {
        return true;
    }

    /**
     * Проверява дали командата изисква потребителят да е излязъл от системата.
     * @return true, ако се изисква излизане, в противен случай false.
     */
    @Override
    public boolean RequiresLogOut() {
        return false;
    }

    /**
     * Проверява дали командата изисква администраторски привилегии.
     * @return true, ако се изискват администраторски привилегии, в противен случай false.
     */
    @Override
    public boolean RequiresAdmin() {
        return false;
    }

    /**
     * Изпълнява логиката за излизане, като изчиства потребителя в текущата сесия.
     * @param context Контекстът на командата.
     * @return true, тъй като излизането винаги е успешно в този контекст.
     */
    @Override
    public boolean Execute(CommandContext context) {
        Container.getInstance().getSession().setUser(null);
        return true;
    }
}
