package fr.iut.montreuil.stationski.Modele;

public class Tour extends Entite {

    private int ptsAttaque;
    private int range;
    private Ennemi ennemiCible;
    private static int prix = 500;

    public Tour (int pv, int posX, int posY, int ptsAttaque, int range, Environnement env){
        super(pv, posX, posY, env);
        this.ptsAttaque=ptsAttaque;
        this.range=range;

    }



public void setRange(int range){this.range = range;}
    public void attaquer(){

        if(ennemiCible != null  ){

            if(isInRange(ennemiCible)) {
                if(ennemiCible.estVivant()) {
                    this.env.getListeProj().add(new Projectile(this.ennemiCible, this.getPosX(), this.getPosY(), this.ptsAttaque));
                } else ennemiCible = null;
            }
            else{
                ennemiCible= searchEnnemi();
                attaquer();

            }

        }else{

            ennemiCible = searchEnnemi();
        }

    }

    public Ennemi searchEnnemi(){
        for(Ennemi e : this.env.getVague().getListEnnemis()){
            if(isInRange(e)){

                return e;

            }
        }
        return null;
    }

    public int getPrix(){
        return prix;
    }


    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(this.getPosY()-ennemi.getPosY())<this.range && Math.abs(this.getPosX()-ennemi.getPosX())<this.range);

    }


    @Override
    public void agit() {

        attaquer();
    }

    public Ennemi getCible(){
        return this.ennemiCible;
    }

    public void setCible(Ennemi e){this.ennemiCible = e;}

    public int getPtsAttaque(){return this.ptsAttaque;}
}