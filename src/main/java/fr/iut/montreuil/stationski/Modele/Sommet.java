package fr.iut.montreuil.stationski.Modele;

public class Sommet {

    private int x;
    private int y;
    private boolean reached ;

    public Sommet (int x, int y, boolean reached){
        this.x = x;
        this.y = y;
        this.reached = reached;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isReached(){return this.reached;}

    public void setReached (boolean reached) {this.reached=reached;}

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Sommet other = (Sommet)obj;
            if (this.x != other.x) {
                return false;
            } else {
                return this.y == other.y;
            }
        }
    }


}
