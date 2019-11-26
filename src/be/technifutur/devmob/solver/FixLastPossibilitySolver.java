package be.technifutur.devmob.solver;

import java.util.Collection;
import java.util.Set;

public class FixLastPossibilitySolver implements SudokuSolver {

    @Override
    public boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion {
        boolean result = false;
        Collection<SudokuWrapper.CelluleWrapper> cells = sudokuWrapper.getCelluleCollection();
        Set<Character> possibilites;
        for(SudokuWrapper.CelluleWrapper c : cells) {
            possibilites = c.getPossibilites();
            if (c.isEmpty() && possibilites.size() == 1) {
                char value = possibilites.stream().findFirst().get();
                sudokuWrapper.setValue(c, value);
                result = true;
            }
        }
        return false;
    }
}
