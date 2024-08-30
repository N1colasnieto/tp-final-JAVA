package ExcepcionesPersonalizadas;

import Juego.Objeto;
import java.util.Collection;
import java.util.Map;

public class MiAyudante
{
    public void validarCaracter(String dato) throws CaracterInvalidoExcepcion
    {
        int cantidadCaracteres = dato.length();
        int flag = 0;

        for (int i = 0; i < cantidadCaracteres && flag == 0; i++)
        {
            if (!Character.isWhitespace(dato.charAt(i)))
            {
                if (!Character.isLetter(dato.charAt(i)))
                {
                    flag = 1;
                }
            }
        }

        if (flag == 1)
        {
            throw new CaracterInvalidoExcepcion("El dato ingresado no debe contener datos que no son caracteres");
        }
    }

    public void existeObjetoEnTienda(String dato ,Map<String, Objeto> aux) throws NoExisteObjetoException
    {
        if(!aux.containsKey(dato))
        {
            throw new NoExisteObjetoException("El nombre ingresado no coincide con ningun objeto en la tienda");
        }
    }
    public void existeObjetoEnInventario(String dato, Collection<Objeto> objetos) throws NoExisteObjetoException
    {
        int flag=0;
           for(Objeto objetito:objetos)
           {
               if(objetito.getNombre().equals(dato))
               {
                   flag=1;

                   break;

               }

           }

        if(flag==0)
        {
            throw new NoExisteObjetoException("El dato ingresado no existe en el inventario, ingrese un dato valido");
        }


    }

    public void validarEleccion(int numeroElegido, int NumeroElecciones) throws EleccionEquivocadaExcepcion
    {
        if(numeroElegido>NumeroElecciones||numeroElegido<=0)
        {
            throw new EleccionEquivocadaExcepcion("Eleccion elegida inexistente, ingrese una valida");
        }
    }

    public void dineroInsuficiente(int dinero, int valorObjeto) throws  DineroInsuficienteException
    {
        if(dinero<valorObjeto)
        {
            throw new DineroInsuficienteException("Tulio dice: No les alcanza el dinero viajeros! Busquen otra cosa o larguense de mi tienda!");
        }
    }

    public void validarRespuesta(String palabra) throws EleccionEquivocadaExcepcion
    {
        if(palabra.equalsIgnoreCase("si") || palabra.equalsIgnoreCase("no"))
        {

        }
        else
        {
            throw new EleccionEquivocadaExcepcion("Eleccion elegida inexistente, ingrese una valida");
        }
    }
    public void existeJugador(String nombre) throws EleccionEquivocadaExcepcion
    {
        int flag=0;

        if(nombre.equalsIgnoreCase("Guts"))
        {
            flag=1;
        }
        if(nombre.equalsIgnoreCase("Legolas"))
        {
            flag=1;
        }
        if(nombre.equalsIgnoreCase("Heiter"))
        {
            flag=1;
        }
        if(nombre.equalsIgnoreCase("Merlin"))
        {
            flag=1;
        }

        if (flag==0)
        {
            throw new EleccionEquivocadaExcepcion("Personaje inexistente, ingrese uno valido");
        }

    }

    public void validarCantidades(int numeroElegido) throws CantidadInvalidaExcepcion
    {
        if(numeroElegido<=0)
        {
            throw new CantidadInvalidaExcepcion("No puedes comprar elementos negativos!");
        }
    }

}
