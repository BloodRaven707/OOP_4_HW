package hero;

public class Point2D {
    public int posX;
    public int posY;

    public Point2D( int x, int y ) {
        this.posX = x;
        this.posY = y;
    }

    public Point2D() {
        this( 0, 0 );
    }


    public String toString() {
        return String.format( "(%d, %d)", this.posX, this.posY );
    }

    protected double getDistance ( Point2D enemy ) {
        return Math.sqrt( Math.pow( posX - enemy.posX, 2 ) + Math.pow( posY - enemy.posY, 2 ) );
    }
}
