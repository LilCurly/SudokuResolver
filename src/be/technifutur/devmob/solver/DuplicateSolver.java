package be.technifutur.devmob.solver;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class DuplicateSolver implements SudokuSolver {
    @Override
    public boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion {
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getLineMap().values()) {
            handleLoop(values, sudokuWrapper);
        }
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getColMap().values()) {
            handleLoop(values, sudokuWrapper);
        }
        for(Collection<SudokuWrapper.CelluleWrapper> values : sudokuWrapper.getSectorMap().values()) {
            handleLoop(values, sudokuWrapper);
        }
        return true;
    }

    private void handleLoop(Collection<SudokuWrapper.CelluleWrapper> values, SudokuWrapper sudokuWrapper) {
        Collection<Character> setValues = values.stream().filter(c -> !c.isEmpty()).map(SudokuWrapper.CelluleWrapper::getValue).collect(Collectors.toCollection(HashSet::new));
        values.stream().filter(SudokuWrapper.CelluleWrapper::isEmpty).forEach(c -> {
            try {
                sudokuWrapper.removePossibilities(c, setValues);
            } catch (SolverExcpetion solverExcpetion) {
                System.out.println("Cannot be removed");
            }
        });
    }
}
