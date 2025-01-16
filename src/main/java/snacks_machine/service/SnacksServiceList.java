package snacks_machine.service;

import java.util.ArrayList;
import java.util.List;
import snacks_machine.domain.Snack;

public class SnacksServiceList implements ISnacksService{

    private static final List<Snack> snacks;

    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));

    }

    @Override
    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    @Override
    public void showSnacks() {
        var inventorySnacks = "";
        for (var snack : snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println("---- Snacks en el inventario ----");
        System.out.println(inventorySnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }

    @Override
    public void deleteSnack(Snack snack) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
