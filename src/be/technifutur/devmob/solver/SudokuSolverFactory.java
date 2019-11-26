package be.technifutur.devmob.solver;

public class SudokuSolverFactory {

    public SudokuSolver getSudokuSolver() {
        RepeatSolver repeat = new RepeatSolver();
        SequenceSolver sequence = new SequenceSolver();
        sequence.addSolver(new DuplicateSolver());
        sequence.addSolver(new FixLastPossibilitySolver());
        repeat.setSolver(sequence);
        return repeat;
    }

}
