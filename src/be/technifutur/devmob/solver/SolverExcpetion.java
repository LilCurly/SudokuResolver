package be.technifutur.devmob.solver;

import be.technifutur.devmob.sudoku.SudokuException;

public class SolverExcpetion extends Exception {
    public SolverExcpetion(SudokuException e) {
        super(e);
    }

    public SolverExcpetion() {
        super();
    }
}
