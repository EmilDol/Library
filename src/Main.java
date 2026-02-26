import  java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to My CLI App!");
    System.out.println("Type something (or 'exit' to quit):");

    while (true) {
        System.out.print("> ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            break;
        }

        System.out.println("You typed: " + input);
    }

    scanner.close();
}
