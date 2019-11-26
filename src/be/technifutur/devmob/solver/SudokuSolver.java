package be.technifutur.devmob.solver;

public interface SudokuSolver {
    boolean resolve(SudokuWrapper sudokuWrapper) throws SolverExcpetion;
}
