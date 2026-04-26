import commands.*;
import container.Container;

import  java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to My CLI App!");
    System.out.println("Type something (or 'exit' to quit):");

    while (true) {
        System.out.print("> ");
        String input = scanner.nextLine();

        ICommand command = null;
        CommandContext context = null;

        switch (input) {
            case "exit":
                return;
            case "open":
                command = CommandFactory.GetCommand(CommandCode.Open);
                break;
        }

        if (command.getClass() != OpenCommand.class && !Container.getInstance().isLoadedFile()) {
            // Не сме заредили файл и се опитваме да правим нещо друго
            continue;
        }

        if (command.RequiresLogOut() && Container.getInstance().getSession().getUser() != null) {
            // Логнати сме, а не трябва да сме
            continue;
        }

        if (command.RequiresLogIn() && Container.getInstance().getSession().getUser() == null) {
            // Не сме логнати, а трябва да сме
            continue;
        }

        if (command.RequiresAdmin() && !Container.getInstance().getSession().getUser().isAdmin()) {
            // Опитваме се да вършим неща за админи
            continue;
        }

        if (!command.Execute(context)) {
            continue;
        }

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            break;
        }

        System.out.println("You typed: " + input);
    }

    scanner.close();
}
