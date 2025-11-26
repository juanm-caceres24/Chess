package src.utils;

public class Movement {

    private Position from;
    private Position to;
    private boolean oldWasMoved;

    public Movement(Position from, Position to, boolean oldWasMoved) {
        this.from = from;
        this.to = to;
        this.oldWasMoved = oldWasMoved;
    }

    /*
     * GETTERS AND SETTERS
     */

    public Position getFrom() { return from; }
    public void setFrom(Position from) { this.from = from; }
    public Position getTo() { return to; }
    public void setTo(Position to) { this.to = to; }
    public boolean getOldWasMoved() { return oldWasMoved; }
    public void setOldWasMoved(boolean oldWasMoved) { this.oldWasMoved = oldWasMoved; }
}
