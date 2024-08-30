package Criatura;

import Juego.Inventario;

public abstract class Personaje extends Ser
{
    protected static Inventario mochila;
    protected static int oro;
    protected boolean defender;

    public Personaje()
    {
        mochila = new Inventario();
        oro = 0;
        defender=false;
    }

    public static void setOro(int oro)
    {
        Personaje.oro = oro;
    }

    public static int getOro()
    {
        return oro;
    }

    public static Inventario getMochila()
    {
        return mochila;
    }

    public void setDefender(boolean defender)
    {
        this.defender = defender;
    }

    public boolean isDefender()
    {
        return defender;
    }
}
