package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionValidMove(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isTargetPathValid(Position targetPosition) {
        Position currentPosition = getPosition();
        while(currentPosition.getXOffset() != targetPosition.getXOffset() && currentPosition.getYOffset() != targetPosition.getYOffset()){
            int xDirection = getDirection(currentPosition.getXOffset(), targetPosition.getXOffset());
            int yDirection = getDirection(currentPosition.getYOffset(), targetPosition.getYOffset());
            currentPosition =  Position.getPositionFor(currentPosition.x + xDirection, currentPosition.y + yDirection);
            if(isTargetPositionNotEmpty(currentPosition)){
                return false;
            }
        }
        return true;
    }

    private boolean isTargetPositionNotEmpty(Position position) {
        return getChessboard().getPieceAt(position) != null;
    }

    private boolean isTargetPositionValidMove(Position targetPosition){
        boolean verticalMoveValid = getPosition().isVerticalMovementValid(targetPosition);
        boolean horizontalMoveValid = getPosition().isHorizontalMovementValid(targetPosition);
        return verticalMoveValid || horizontalMoveValid;
    }

}
