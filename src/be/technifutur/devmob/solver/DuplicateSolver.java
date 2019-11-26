package be.technifutur.devmob.solver;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class DuplicateSolver implements SudokuSolver {
    @Override
    public boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion {
        boolean result = false;
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getLineMap().values()) {
            result |= handleLoop(values, sudokuWrapper);
        }
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getColMap().values()) {
            result |= handleLoop(values, sudokuWrapper);
        }
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getSectorMap().values()) {
            result |= handleLoop(values, sudokuWrapper);
        }
        return result;
    }

    private boolean handleLoop(Collection<SudokuWrapper.CelluleWrapper> values, SudokuWrapper sudokuWrapper) {
        boolean resolved = false;
        Collection<Character> setValues = values.stream().filter(c -> !c.isEmpty()).map(SudokuWrapper.CelluleWrapper::getValue).collect(Collectors.toCollection(HashSet::new));
        if(setValues.size() > 0 && setValues.size() < values.size()) resolved = true;
        values.stream().filter(SudokuWrapper.CelluleWrapper::isEmpty).forEach(c -> {
            try {
                sudokuWrapper.removePossibilities(c, setValues);
            } catch (SolverExcpetion solverExcpetion) {
                System.out.println("Cannot be removed");
            }
        });
        return resolved;
    }
}
