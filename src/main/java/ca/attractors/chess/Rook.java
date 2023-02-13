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
        if (!isTargetPositionOnSameRankOrFile(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (isTargetPathValidY(targetPosition)) return false;
        if (isTargetPathValidX(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isTargetPathValidX(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int direction = start > end ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int x = start+direction; x != end; x = x + direction) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isTargetPathValidY(Position targetPosition) {

        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int direction = start > end ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int y = start + direction; y != end; y = y + direction) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isTargetPieceSameColour(ChessPiece targetPiece) {
        if (targetPiece != null) {
            return targetPiece.getColor() == getColor();
        }
        return false;
    }

    private boolean isTargetPositionOnSameRankOrFile(Position targetPosition) {
        boolean isSameFile = targetPosition.x == getPosition().x;
        boolean isSameRank = targetPosition.y == getPosition().y;
        return  (isSameRank && !isSameFile) || (!isSameRank && isSameFile);
    }
}
