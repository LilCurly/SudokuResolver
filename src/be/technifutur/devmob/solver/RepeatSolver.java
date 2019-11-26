package be.technifutur.devmob.solver;

public class RepeatSolver implements SudokuSolver {
    private SudokuSolver solver;

    @Override
    public boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion {
        boolean repeat = true;
        while (repeat) {
            repeat = solver.resolve(sudokuWrapper);
        }
        return true;
    }

    public void setSolver(SudokuSolver solv) {
        this.solver = solv;
    }
}
