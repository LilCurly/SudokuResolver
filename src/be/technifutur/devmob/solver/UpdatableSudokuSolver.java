package be.technifutur.devmob.solver;

import be.technifutur.devmob.sudoku.sudoku_interfaces.UpdatableSudoku;

public class UpdatableSudokuSolver {

    public boolean resolve(UpdatableSudoku sudoku) throws SolverExcpetion {
        SudokuWrapper wrapper = new SudokuWrapper(sudoku);
        return new SudokuSolverFactory().getSudokuSolver().resolve(wrapper);
    }

}
