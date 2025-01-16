package snacks_machine.service;

import java.util.List;
import snacks_machine.domain.Snack;

public interface ISnacksService {
    
    public abstract void addSnack(Snack snack);
    public abstract void showSnacks();
    public abstract void deleteSnack(Snack snack);
    public abstract List<Snack> getSnacks();
}
