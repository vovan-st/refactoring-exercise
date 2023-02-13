package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    public boolean isValidMove(Position targetPosition) {
        return false;
    }
}
