package snacks_machine.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import snacks_machine.domain.Snack;
import snacks_machine.service.ISnacksService;
import snacks_machine.service.SnacksServiceFile;

public class SnackMachine {

    public static void executeProcess() {
        var exit = false;
        var console = new Scanner(System.in);

        //ISnacksService snacksService = new SnacksServiceList();
        ISnacksService snacksService = new SnacksServiceFile();
        List<Snack> products = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        snacksService.showSnacks();
        while (!exit) {
            try {
                var option = showMenu(console);
                exit = executeOptions(option, console, products, snacksService);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner console) {
        System.out.println("""
                           Menu:
                           1. Compra snack
                           2. Mostrar ticket
                           3. Agregar nuevo snack
                           4. Eliminar snack
                           5. Mostrar inventario actual
                           6. Salir
                           Elije una opcion:\s""");
        return Integer.parseInt(console.nextLine());
    }

    private static boolean executeOptions(int option, Scanner console, List<Snack> products, ISnacksService snacksService) {
        var exit = false;
        switch (option) {
            case 1 ->
                buySnack(console, products, snacksService);
            case 2 ->
                showTicket(products);
            case 3 ->
                addNewSnack(console, snacksService);
            case 4 ->
                deleteSnack(console, snacksService);
            case 5 ->
                showInventory(snacksService);
            case 6 -> {
                System.out.println("Regresa pronto!");
                exit = true;
            }
            default ->
                System.out.println("Opcion invalida: " + option);
        }
        return exit;
    }

    private static void buySnack(Scanner console, List<Snack> products, ISnacksService snacksService) {
        System.out.println("Que snack quieres comprar (id)?");
        var idSnack = Integer.parseInt(console.nextLine());
        var foundSnack = false;
        for (var snack : snacksService.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                products.add(snack);
                System.out.println("Ok, Snack agregado: " + snack);
                foundSnack = true;
                break;
            }
        }

        if (!foundSnack) {
            System.out.println("Id de snack no encontrado: " + idSnack);
        }

    }

    private static void showTicket(List<Snack> products) {
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;
        for (var product : products) {
            ticket += "\n\t-" + product.getName() + " - $" + product.getPrice();
            total += product.getPrice();
        }

        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void addNewSnack(Scanner console, ISnacksService snacksService) {
        System.out.println("Nombre del snack: ");
        var name = console.nextLine();
        System.out.println("Precio del snack: ");
        var price = Double.parseDouble(console.nextLine());
        List<Snack> list = snacksService.getSnacks();
        snacksService.addSnack(new Snack(list.get(list.size() - 1).getIdSnack() + 1, name, price));
        System.out.println("Tu snack se ha agregado correctamente");
        snacksService.showSnacks();
    }

    private static void deleteSnack(Scanner console, ISnacksService snacksService) {
        System.out.println("Que snack quieres eliminar (id)?");
        var idSnack = Integer.parseInt(console.nextLine());
        for (var snack : snacksService.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                snacksService.deleteSnack(snack);
                break;
            }
        }
    }

    private static void showInventory(ISnacksService snacksService) {
        snacksService.showSnacks();
    }

    public static void main(String[] args) {
        executeProcess();
    }

}
