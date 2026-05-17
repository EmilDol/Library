package data.models;

/**
 * Представлява потребител в системата.
 *
 * @author Емил Долчинков
 */
public class User {

    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Създава потребител с указаните потребителско име, парола и администраторски статус.
     *
     * @param username Потребителското име на потребителя.
     * @param password Паролата на потребителя.
     * @param isAdmin  Дали потребителят има администраторски привилегии.
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Конструктор по подразбиране за потребител.
     */
    public User() {
    }

    // Гетъри и сетъри

    /**
     * Връща потребителското име на потребителя.
     *
     * @return Потребителското име.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Задава потребителското име на потребителя.
     *
     * @param username Потребителското име, което да бъде зададено.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Връща паролата на потребителя.
     *
     * @return Паролата.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Задава паролата на потребителя.
     *
     * @param password Паролата, която да бъде зададена.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Проверява дали потребителят е администратор.
     *
     * @return true, ако потребителят е администратор, false в противен случай.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Задава администраторския статус на потребителя.
     *
     * @param admin true, за да направите потребителя администратор, false в противен случай.
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}