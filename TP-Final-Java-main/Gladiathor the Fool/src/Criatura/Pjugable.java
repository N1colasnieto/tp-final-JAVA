package Criatura;

public class Pjugable extends Personaje
{
    public Pjugable()
    {

    }

    public void setMago()
    {
        nombre="Merlin";
        vidaMax=40;
        vidaActual=vidaMax;
        atk=50;
        velocidad=25;
    }

    public void setGuerrero()
    {
        nombre="Guts";
        vidaMax=110;
        vidaActual=vidaMax;
        atk=15;
        velocidad=26;
    }

    public void setArquero()
    {
        nombre="Legolas";
        vidaMax=40;
        vidaActual=vidaMax;
        atk=15;
        velocidad=69;
    }

    public void setClerigo()
    {
        nombre="Heiter";
        vidaMax=70;
        vidaActual=vidaMax;
        atk=30;
        velocidad=50;
    }
}
