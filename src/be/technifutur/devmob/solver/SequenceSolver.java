package be.technifutur.devmob.solver;

import java.util.ArrayList;
import java.util.Collection;

public class SequenceSolver implements SudokuSolver {
    private Collection<SudokuSolver> solvers = new ArrayList<>();

    @Override
    public boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion {
        boolean result = false;
        for(SudokuSolver solv : solvers) {
            result |= solv.resolve(sudokuWrapper);
        }
        return result;
    }

    public void addSolver(SudokuSolver solver) {
        solvers.add(solver);
    }
}
