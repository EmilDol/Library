package commands;

/**
 * Изброяване на наличните кодове за команди в приложението.
 *
 * @author Емил Долчинков
 */
public enum CommandCode {
    /** Команда за влизане */
    LOG_IN("login"),
    /** Команда за излизане */
    LOG_OUT("logout"),
    /** Команда за отваряне на файл */
    OPEN("open"),
    /** Команда за затваряне на файл */
    CLOSE("close"),
    /** Команда за запазване на текущия файл */
    SAVE("save"),
    /** Команда за запазване на текущия файл като */
    SAVE_AS("save as"),
    /** Команда за помощ */
    HELP("help"),
    /** Команда за изход от приложението */
    EXIT("exit"),
    /** Команда за списък на всички книги */
    BOOKS_ALL("books all"),
    /** Команда за намиране на книги */
    BOOKS_FIND("books find"),
    /** Команда за сортиране на книги */
    BOOKS_SORT("books sort"),
    /** Команда за добавяне на книга */
    BOOKS_ADD("books add"),
    /** Команда за премахване на книга */
    BOOKS_REMOVE("books remove"),
    /** Команда за добавяне на потребител */
    USERS_ADD("users add"),
    /** Команда за премахване на потребител */
    USERS_REMOVE("users remove"),
    /** Стойност при невалиден вход от потребителя */
    ILLEGAL_COMMAND(null);

    private final String text;

    CommandCode(String text) {
        this.text = text;
    }

    public static CommandCode fromString(String input) {
        if (input == null) return ILLEGAL_COMMAND;

        for (CommandCode c : values()) {
            if (c.text != null && c.text.equalsIgnoreCase(input)) {
                return c;
            }
        }

        return ILLEGAL_COMMAND;
    }

    public String getText() {
        return text;
    }
}
