import commands.*;
import container.Container;
import data.repositories.BookXmlRepository;
import data.repositories.UserXmlRepository;
import data.repositories.contracts.BookRepository;
import data.repositories.contracts.UserRepository;

import  java.util.Scanner;

/**
 * Основна входна точка за системата за управление на библиотека.
 *
 * @author Емил Долчинков
 */
void main() {
    Setup();

    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to my library management system!");
    System.out.println("Type something (or 'help' to view all commands):");

    while (true) {
        System.out.print("> ");
        String input = scanner.nextLine();

        input = input.toLowerCase();

        Command command = null;
        CommandContext context = new CommandContext();

        switch (input) {
            case "exit": {
                command = CommandFactory.GetCommand(CommandCode.Exit);
                break;
            }
            case "open": {
                command = CommandFactory.GetCommand(CommandCode.Open);
                System.out.println("File name: ");
                String filename = scanner.nextLine();
                context.put("filename", filename);
                break;
            }
            case "close": {
                command = CommandFactory.GetCommand(CommandCode.Close);
                break;
                }
            case "save": {
                command = CommandFactory.GetCommand(CommandCode.Save);
                break;
            }
            case "save as": {
                command = CommandFactory.GetCommand(CommandCode.SaveAs);
                System.out.println("File name: ");
                String filename = scanner.nextLine();
                context.put("filename", filename);
                break;
            }
            case "help": {
                command = CommandFactory.GetCommand(CommandCode.Help);
                break;
            }
            case "login": {
                command = CommandFactory.GetCommand(CommandCode.LogIn);
                System.out.println("Username: ");
                String username = scanner.nextLine();
                context.put("username", username);
                System.out.println("Password: ");
                String password = scanner.nextLine();
                context.put("password", password);
                break;
            }
            case "logout": {
                command = CommandFactory.GetCommand(CommandCode.LogOut);
                break;
            }
            case "books all": {
                command = CommandFactory.GetCommand(CommandCode.BooksAll);
                break;
            }
            case "books find": {
                command = CommandFactory.GetCommand(CommandCode.BooksFind);
                System.out.println("Book name: ");
                String bookname = scanner.nextLine();
                context.put("value", bookname);
                break;
            }
            case "books sort": {
                command = CommandFactory.GetCommand(CommandCode.BooksSort);
                System.out.println("Direction(asc, desc): ");
                String dir = scanner.nextLine();
                context.put("dir", dir);
                break;
            }
            case "books add": {
                command = CommandFactory.GetCommand(CommandCode.BooksAdd);

                System.out.println("Name: ");
                String bookname = scanner.nextLine();
                context.put("bookname", bookname);

                System.out.println("Author: ");
                String author = scanner.nextLine();
                context.put("author", author);

                System.out.println("Genre: ");
                String genre = scanner.nextLine();
                context.put("genre", genre);

                System.out.println("Description: ");
                String description = scanner.nextLine();
                context.put("description", description);

                System.out.println("Year: ");
                Integer year = Integer.parseInt(scanner.nextLine());
                context.put("year", year);

                System.out.println("Rating: ");
                Double rating = Double.parseDouble(scanner.nextLine());
                context.put("rating", rating);

                System.out.println("Keywords (separated by \",\"): ");
                String keywords = scanner.nextLine();
                context.put("keywords", keywords);
                break;
            }
            case "books remove": {
                command = CommandFactory.GetCommand(CommandCode.BooksRemove);
                System.out.println("Book id: ");
                Integer id = Integer.parseInt(scanner.nextLine());
                context.put("id", id);
                break;
            }
            case "users add": {
                command = CommandFactory.GetCommand(CommandCode.UsersAdd);
                System.out.println("Username: ");
                String username = scanner.nextLine();
                context.put("username", username);

                System.out.println("Password: ");
                String password = scanner.nextLine();
                context.put("password", password);
                break;
            }
            case "users remove": {
                command = CommandFactory.GetCommand(CommandCode.UsersRemove);
                System.out.println("Username: ");
                String username = scanner.nextLine();
                context.put("username", username);
                break;
            }
            default:
                System.out.println("Invalid command!");
                continue;
        }

        if ((command.getClass() != OpenCommand.class && command.getClass() != HelpCommand.class) && !Container.getInstance().isLoadedFile()) {
            // Не сме заредили файл и се опитваме да правим нещо друго
            System.out.println("No file is loaded! First run the open command!");
            continue;
        }

        if (command.getClass() == OpenCommand.class && Container.getInstance().isLoadedFile()) {
            // Заредили сме файл и се опитваме да заредим нов
            System.out.println("There is already an opened file! Either close it or save it before opening a new one!");
            continue;
        }

        if (command.RequiresLogOut() && Container.getInstance().getSession().getUser() != null) {
            // Логнати сме, а не трябва да сме
            System.out.println("You are logged in! Log out first to use this command!");
            continue;
        }

        if (command.RequiresLogIn() && Container.getInstance().getSession().getUser() == null) {
            // Не сме логнати, а трябва да сме
            System.out.println("You are not logged in! Log in first to run this command!");
            continue;
        }

        if (command.RequiresAdmin() && !Container.getInstance().getSession().getUser().isAdmin()) {
            // Опитваме се да вършим неща за админи
            System.out.println("You are not admin! You cannot run this command!");
            continue;
        }

        if (!command.Execute(context)) {
            System.out.println("Fail");
            continue;
        }

        if (input.equalsIgnoreCase("exit")) {
            break;
        }

        System.out.println("Success");
    }

    scanner.close();
}

/**
 * Конфигурира приложението чрез регистриране на хранилища и инициализиране на сесията.
 */
void Setup() {
    Container.getInstance().addRepository(BookRepository.class, BookXmlRepository.class);
    Container.getInstance().addRepository(UserRepository.class, UserXmlRepository.class);

    Container.getInstance().unloadFile();

    Container.getInstance().getSession().setUser(null);
}