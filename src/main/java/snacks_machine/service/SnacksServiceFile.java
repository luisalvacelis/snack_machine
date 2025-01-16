package snacks_machine.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import snacks_machine.domain.Snack;

public class SnacksServiceFile implements ISnacksService {

    private final String FILE_NAME;
    private List<Snack> snacks;

    public SnacksServiceFile() {
        this.FILE_NAME = "snacks.txt";
        this.snacks = new ArrayList<>();
        var file = new File(FILE_NAME);
        var exists = validateFile(file);
        if (!exists) {
            this.createFile(file);
            this.loadInitialSnacks();
        } else {
            this.loadSnacks();
        }
    }

    private boolean validateFile(File file) {
        boolean status = false;
        try {
            status = file.exists();
        } catch (Exception e) {
            System.out.println("Error al validar el archivo: " + e.getMessage());
        }
        return status;
    }

    private void createFile(File file) {
        try {
            var output = new PrintWriter(new FileWriter(file));
            output.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    private void loadInitialSnacks() {
        this.addSnack(new Snack("Papas", 70));
        this.addSnack(new Snack("Refresco", 50));
        this.addSnack(new Snack("Sandwich", 120));
    }

    private void loadSnacks() {
        snacks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            lines.forEach(line -> {
                String[] snackLine = line.split(",");
                var idSnack = Integer.parseInt(snackLine[0]);
                var name = snackLine[1];
                var price = Double.parseDouble(snackLine[2]);
                var snack = new Snack(idSnack, name, price);
                snacks.add(snack);
            });
        } catch (IOException e) {
            System.out.println("Error al leer archivo de snacks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addSnack(Snack snack) {
        this.snacks.add(snack);
        this.addSnackToFile(snack);
    }

    private void addSnackToFile(Snack snack) {
        boolean append = false;
        var file = new File(FILE_NAME);
        try {
            append = file.exists();
            var output = new PrintWriter(new FileWriter(file, append));
            output.println(snack.writeSnack());
            output.close();
        } catch (IOException e) {
            System.out.println("Error al agregar snack: " + e.getMessage());
        }
    }

    @Override
    public void showSnacks() {
        System.out.println("---- Snacks en el inventario ----");
        var inventorySnacks = "";
        for (var snack : snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println(inventorySnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }

    @Override
    public void deleteSnack(Snack snack) {
        this.snacks.remove(snack);
        this.deleteSnackToFile(snack);
    }

    private void deleteSnackToFile(Snack snack) {
        var file = new File(FILE_NAME);
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            List<String> updatedLines = new ArrayList<>();
            lines.forEach(line -> {
                String[] snackLine = line.split(",");
                int idSnack = Integer.parseInt(snackLine[0]);
                if (idSnack != snack.getIdSnack()) {
                    updatedLines.add(line);
                }
            });

            System.out.println("Lineas actualizadas: " + updatedLines);

            try (var output = new PrintWriter(new FileWriter(file, false))) {
                for (String updatedLine : updatedLines) {
                    output.println(updatedLine);
                }
            }

            System.out.println("Se ha eliminado correctamente el snack.");
        } catch (IOException e) {
            System.out.println("Error al eliminar snack: " + e.getMessage());
        }
    }
}
