package Juego;

import Criatura.Personaje;
import Criatura.Ser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventario implements IMostrarObjeto
{
    private final Map<String, Objeto> objetosActivos;

    public Inventario()
    {
        objetosActivos = new HashMap<>();
    }

    @Override
    public void mostrarObjetos()
    {
        System.out.println("Objetos Activos: ");

        for(Objeto a : objetosActivos.values())
        {
            System.out.println("Objeto: " +a.getNombre());
            System.out.println("cantidad disponible: " + a.getCantidad());
            System.out.println("Afecta a: " + a.getStat() + " aumentandolo en " + a.getCambioStat());
        }
    }

    public Collection<Objeto> getObjetosActivos() {
        return objetosActivos.values();
    }

    public void agregarObjetoPasivo(Objeto objeto, ArrayList<Personaje> jugador)
    {
        aumentarStat(objeto, jugador);
    }

    public void agregarObjetoActivo(Objeto objeto)
    {
        if(objetosActivos.containsKey(objeto.getNombre()))
        {
            int aux=objetosActivos.get(objeto.getNombre()).getCantidad()+objeto.getCantidad();
            objetosActivos.get(objeto.getNombre()).setCantidad(aux);
        }
        else
        {
            objetosActivos.put(objeto.getNombre(), objeto);
        }

    }

    public void usarObjetoActivo(String nombre, Ser combatiente)
    {
        if(objetosActivos.containsKey(nombre))
        {
            switch (objetosActivos.get(nombre).getNombre())
            {
                case "Chile de la Ira":
                    int atk= combatiente.getAtk()+objetosActivos.get(nombre).getCambioStat();
                    combatiente.setAtk(atk);
                    objetosActivos.get(nombre).setCantidad(objetosActivos.get(nombre).getCantidad()-1);

                    System.out.printf(combatiente.getNombre() + " ha aumentado su ataque en " + objetosActivos.get(nombre).getCambioStat());

                    if (objetosActivos.get(nombre).getCantidad()<=0)
                    {
                        objetosActivos.remove(nombre);
                    }
                    break;

                case "Pata de Conejo":
                    int velocidad= combatiente.getVelocidad()+objetosActivos.get(nombre).getCambioStat();
                    combatiente.setVelocidad(velocidad);
                    objetosActivos.get(nombre).setCantidad(objetosActivos.get(nombre).getCantidad()-1);

                    System.out.printf(combatiente.getNombre() + " ha aumentado su velocidad en " + objetosActivos.get(nombre).getCambioStat());

                    if (objetosActivos.get(nombre).getCantidad()<=0)
                    {
                        objetosActivos.remove(nombre);
                    }
                    break;

                case "Lagrimas de Fenix":
                    combatiente.setVidaActual(combatiente.getVidaMax());
                    objetosActivos.get(nombre).setCantidad(objetosActivos.get(nombre).getCantidad()-1);

                    System.out.println(combatiente.getNombre() + " ha revivido!");

                    if (objetosActivos.get(nombre).getCantidad()<=0)
                    {
                        objetosActivos.remove(nombre);
                    }
                    break;

                case "Pocion Curativa":
                    int vida= combatiente.getVidaActual()+objetosActivos.get(nombre).getCambioStat();
                    combatiente.setVidaActual(Math.min(vida, combatiente.getVidaMax()));
                    objetosActivos.get(nombre).setCantidad(objetosActivos.get(nombre).getCantidad()-1);

                    System.out.println(combatiente.getNombre() + " se ha curado " + objetosActivos.get(nombre).getCambioStat());

                    if (objetosActivos.get(nombre).getCantidad()<=0)
                    {
                        objetosActivos.remove(nombre);
                    }
                    break;

                case "Pocion Explosiva":
                    int danio= combatiente.getVidaActual()-objetosActivos.get(nombre).getCambioStat();
                    combatiente.setVidaActual(danio);
                    objetosActivos.get(nombre).setCantidad(objetosActivos.get(nombre).getCantidad()-1);

                    System.out.println(combatiente.getNombre() + " ha sufrido " + objetosActivos.get(nombre).getCambioStat());

                    if (objetosActivos.get(nombre).getCantidad()<=0)
                    {
                        objetosActivos.remove(nombre);
                    }
                    break;
            }
        }
    }

    public void aumentarStat(Objeto objeto, ArrayList<Personaje> jugador)
    {
        switch (objeto.getStat())
        {
            case "atk":
                for (Personaje personaje : jugador)
                {
                    int atk= personaje.getAtk()+objeto.getCambioStat();
                    personaje.setAtk(atk);
                }
                break;

            case "vidaMax":
                for (Personaje personaje : jugador)
                {
                    int vidaMax= personaje.getVidaMax()+objeto.getCambioStat();
                    personaje.setVidaMax(vidaMax);
                    personaje.setVidaActual(vidaMax);
                }
                break;

            case "velocidad":
                for (Personaje personaje : jugador)
                {
                    int velocidad= personaje.getVelocidad()+objeto.getCambioStat();
                    personaje.setVelocidad(velocidad);
                }
                break;
        }
    }

    public boolean hayObjetosActivos()
    {
        return !objetosActivos.isEmpty();
    }
}