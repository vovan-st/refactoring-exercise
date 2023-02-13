package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.A1;
import static ca.attractors.chess.Position.A2;
import static ca.attractors.chess.Position.A3;
import static ca.attractors.chess.Position.B1;
import static ca.attractors.chess.Position.B2;
import static ca.attractors.chess.Position.C1;
import static ca.attractors.chess.Position.D1;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    private Board board;

    @BeforeEach
    void initialize() {
        board = new Board();
    }

    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        Rook rook1 = new Rook(White, board, A1);
        assertFalse(rook1.moveTo(B2));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        Rook rook = new Rook(White, board, A1);
        assertTrue(rook.moveTo(A3));
        assertSame(rook.getPosition(), A3);
    }
    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        Rook rook1 = new Rook(White, board, A1);
        Rook rook2 = new Rook(Black, board, A2);
        assertFalse(rook1.moveTo(A3));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        Rook rook1 = new Rook(White, board, A1);
        assertTrue(rook1.moveTo(C1));
        assertSame(rook1.getPosition(), C1);
    }


    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Rook rook1 = new Rook(White, board, A1);
        Rook rook2 = new Rook(Black, board, B1);
        assertFalse(rook1.moveTo(C1));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Rook rook1 = new Rook(White, board, A1);
        Rook rook2 = new Rook(White, board, A3);
        assertFalse(rook1.moveTo(A3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Rook rook1 = new Rook(White, board, A1);
        Rook rook2 = new Rook(Black, board,D1);
        assertTrue(rook1.moveTo(D1));
        assertSame(rook1.getPosition(), D1);
        assertNull(rook2.getPosition());
        assertNull (board.getPieceAt(A1));
    }
}
