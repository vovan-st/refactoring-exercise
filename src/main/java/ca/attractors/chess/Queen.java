package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionValidMove(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (!isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isTargetPositionValidMove(Position targetPosition){
        boolean verticalMoveValid = getPosition().isVerticalMovementValid(targetPosition);
        boolean horizontalMoveValid = getPosition().isHorizontalMovementValid(targetPosition);
        boolean diagonalMoveValid = getPosition().isDiagonalMoveValid(targetPosition);
        return verticalMoveValid || horizontalMoveValid || diagonalMoveValid;
    }
}
