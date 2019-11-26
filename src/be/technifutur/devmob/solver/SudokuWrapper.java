package be.technifutur.devmob.solver;

import be.technifutur.devmob.sudoku.PositionFactory;
import be.technifutur.devmob.sudoku.SudokuException;
import be.technifutur.devmob.sudoku.sudoku_interfaces.Position;
import be.technifutur.devmob.sudoku.sudoku_interfaces.UpdatableSudoku;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuWrapper {
    private UpdatableSudoku sudoku;
    private Collection<CelluleWrapper> cellCollection;
    private Map<Integer, Collection<CelluleWrapper>> lineMap;

    public SudokuWrapper(UpdatableSudoku sudoku) {
        this.sudoku = sudoku;
    }

    public Collection<CelluleWrapper> getCelluleCollection() {
        if (cellCollection == null) {
            cellCollection = new ArrayList<>();
            Position p;
            for (int i = 0; i < sudoku.size(); i++) {
                p = PositionFactory.get(sudoku, i);
                if(sudoku.isEmpty(p)) {
                    cellCollection.add(new CelluleWrapper(p, sudoku.remainingValues()));
                }
                else {
                    cellCollection.add(new CelluleWrapper(p, sudoku.get(p)));
                }
            }
        }
        return cellCollection;
    }

    public Map<Integer, Collection<CelluleWrapper>> getLineMap() {
        return getCelluleCollection().stream().collect(Collectors.groupingBy(c -> c.position.getRow(), Collectors.toCollection(HashSet::new)));
    }

    public Map<Integer, Collection<CelluleWrapper>> getColMap() {
        return getCelluleCollection().stream().collect(Collectors.groupingBy(c -> c.position.getCol(), Collectors.toCollection(HashSet::new)));
    }

    public Map<Integer, Collection<CelluleWrapper>> getSectorMap() {
        return getCelluleCollection().stream().collect(Collectors.groupingBy(c -> c.position.getSector(), Collectors.toCollection(HashSet::new)));
    }

    public boolean setValue(CelluleWrapper c, char value) throws SolverExcpetion {
        try {
            this.sudoku.add(c.position, value);
            c.possibilites = null;
            c.value = value;
            return true;
        } catch (SudokuException e) {
            throw new SolverExcpetion(e);
        }
    }

    public boolean removePossibilities(CelluleWrapper c, Collection<Character> value) throws SolverExcpetion {
        if(c.possibilites.size() == 1) {
            throw new SolverExcpetion();
        }
        c.possibilites.removeAll(value);
        return true;
    }

    public class CelluleWrapper {
        private Position position;
        private Set<Character> possibilites;
        private char value;

        public CelluleWrapper(Position p, Set<Character> possibilites) {
            this.position = p;
            this.possibilites = Set.copyOf(possibilites);
        }

        public CelluleWrapper(Position p, char value) {
            this.position = p;
            this.possibilites = null;
            this.value = value;
        }

        public boolean isEmpty() {
            return this.possibilites != null && value == Character.MIN_VALUE;
        }

        public char getValue() {
            return this.value;
        }

        public Set<Character> getPossibilites() {
            return Collections.unmodifiableSet(possibilites);
        }
    }

}
