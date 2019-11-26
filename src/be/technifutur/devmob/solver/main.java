package be.technifutur.devmob.solver;

import be.technifutur.devmob.sudoku.sudoku_interfaces.*;
import be.technifutur.devmob.sudoku.utils.UserConsole;

public class main {

    public static void main(String[] args) {
        AbstractSudokuController ctrl = AbstractSudokuFactory.getSudoku(SudokuType.SUDOKU_9_9);
        AbstractSudoku model = AbstractSudokuFactory.getSudokuModel(SudokuType.SUDOKU_9_9);
        AbstractSudokuView view = AbstractSudokuFactory.getSudokuView(model, new UserConsole());
        UpdatableSudokuSolver solver = new UpdatableSudokuSolver();
        ctrl.start();
        try {
            solver.resolve(model);
        } catch (SolverExcpetion solverExcpetion) {
            view.show("Error");
        }
    }

}
