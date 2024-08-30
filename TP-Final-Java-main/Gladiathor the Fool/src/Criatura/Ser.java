package Criatura;

public class Ser
{
    protected String nombre;
    protected int vidaMax;
    protected int vidaActual;
    protected int atk;
    protected int velocidad;

    public Ser(int nroNivel)
    {
        nombre = " ";
        vidaMax = 60*nroNivel;
        vidaActual = 60*nroNivel;
        atk = 6*nroNivel;
        velocidad = 9*nroNivel;
    }

    public Ser()
    {
        nombre=" ";
        vidaMax=0;
        vidaActual=0;
        atk=0;
        velocidad=0;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setVidaMax(int vidaMax)
    {
        this.vidaMax = vidaMax;
    }

    public void setVidaActual(int vidaActual)
    {
        this.vidaActual = vidaActual;
    }

    public void setAtk(int atk)
    {
        this.atk = atk;
    }

    public void setVelocidad(int velocidad)
    {
        this.velocidad = velocidad;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getVidaMax()
    {
        return vidaMax;
    }

    public int getVidaActual()
    {
        return vidaActual;
    }

    public int getAtk()
    {
        return atk;
    }

    public int getVelocidad()
    {
        return velocidad;
    }
}
