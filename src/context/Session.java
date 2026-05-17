package context;

import data.models.User;

/**
 * Клас, представляващ текущата потребителска сесия в приложението.
 * @author Емил Долчинков
 */
public class Session {

    private User user;

    /**
     * Връща текущо влезлия потребител.
     * @return Обектът User или null, ако никой потребител не е влязъл.
     */
    public User getUser() {
        return user;
    }

    /**
     * Задава текущо влезлия потребител.
     * @param user Обектът User, който да се зададе в сесията.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
