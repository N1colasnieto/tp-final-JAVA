package Juego;

import ExcepcionesPersonalizadas.*;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Tienda implements IMostrarObjeto
{
    private  Map<String, Objeto> objetos;
    private int dineroGastado;

    public Tienda()
    {
        objetos = new HashMap<>();
        dineroGastado=0;
    }

    public Map<String, Objeto> getObjetos() {
        return objetos;
    }

    public void agregarObjetos(Objeto dato)
    {
        objetos.put(dato.getNombre(), dato);
    }
    public Objeto menu(int dinero)
    {
        MiAyudante ayudante=new MiAyudante();
        boolean tieneError;
        Scanner scanner = new Scanner(System.in);

        do
        {
            tieneError=false;
            System.out.println("\nBIEEEENVENIDO A LA TIENDA. Desea hacer alguna compra? (escriba 'si' para hacerlo)");
            System.out.println("(si no deseas comprar nada, escriba 'no')");

            try
            {
                String selector = scanner.next();
                ayudante.validarCaracter(selector);
                ayudante.validarRespuesta(selector);

                if (selector.equalsIgnoreCase("si"))
                {
                    mostrarObjetos();
                    Objeto compra = comprar(dinero);

                    if (compra != null)
                    {
                        System.out.println("Has comprado " + compra.getNombre() + ". Tulio te dice: Un placer hacer negocios con ustedes!");
                        return compra;
                    }
                }
                else
                {
                    return null;
                }
            }
            catch (CaracterInvalidoExcepcion | EleccionEquivocadaExcepcion e)
            {
                System.out.println("Error:"+e.getMessage());
                tieneError=true;
            }
        }
        while (tieneError);

        return null;
    }
    @Override
    public void mostrarObjetos()
    {
         for (Objeto dato: objetos.values()) {
             if(dato.isEsActivo()) {
                 System.out.println(dato.toString());
                 System.out.println("Tipo de Objeto: activo");

             }
             else if(dato.isEsPasivo())
             {
                 System.out.println(dato.toString());
                 System.out.println("Tipo de Objeto: pasivo");

             }


         }

    }

    public void agregar()
    {
        Objeto aux=new Objeto("Espada Maestra", 45, 10, "atk");
        aux.setEsPasivo(true);
        objetos.put(aux.getNombre(), aux);
        Objeto au8=new Objeto("Arco de la vida eterna", 65, 20, "atk");
        au8.setEsPasivo(true);
        objetos.put(au8.getNombre(), au8);
        Objeto au11=new Objeto("Tonico Vigorizante", 90, 30, "atk");
        au11.setEsPasivo(true);
        objetos.put(au11.getNombre(), au11);
        Objeto au1=new Objeto("Cruz del alba", 45, 100, "vidaMax");
        au1.setEsPasivo(true);
        objetos.put(au1.getNombre(), au1);
        Objeto au5=new Objeto("Tarta de manzana sospechosa", 65, 200, "vidaMax");
        au5.setEsPasivo(true);
        objetos.put(au5.getNombre(), au5);
        Objeto au9=new Objeto("Sopa de la abuela", 90, 300, "vidaMax");
        au9.setEsPasivo(true);
        objetos.put(au9.getNombre(), au9);
        Objeto au2=new Objeto("Botas Flotantes", 45, 20, "velocidad");
        au2.setEsPasivo(true);
        objetos.put(au2.getNombre(), au2);
        Objeto au6=new Objeto("Parche de clarividencia", 65, 30, "velocidad");
        au6.setEsPasivo(true);
        objetos.put(au6.getNombre(), au6);
        Objeto au10=new Objeto("Flecha veloz", 90, 40, "velocidad");
        au10.setEsPasivo(true);
        objetos.put(au10.getNombre(), au10);

        Objeto au12=new Objeto("Chile de la Ira", 25, 10, "atk");
        au12.setEsActivo(true);
        objetos.put(au12.getNombre(), au12);
        Objeto au14=new Objeto("Pata de Conejo", 25, 20, "velocidad");
        au14.setEsActivo(true);
        objetos.put(au14.getNombre(), au14);
        Objeto au16=new Objeto("Lagrimas de Fenix", 100, 999, "vidaActual");
        au16.setEsActivo(true);
        objetos.put(au16.getNombre(), au16);
        Objeto au17=new Objeto("Pocion Curativa", 20, 50, "vidaActual");
        au17.setEsActivo(true);
        objetos.put(au17.getNombre(), au17);
        Objeto au18=new Objeto("Pocion Explosiva", 20, 20, "vidaActual");
        au18.setEsActivo(true);
        objetos.put(au18.getNombre(), au18);
    }

    private Objeto comprar(int dinero)
    {
        MiAyudante dato=new MiAyudante();
        Scanner scanner=new Scanner(System.in);
        boolean tieneExepcion;

        do
        {
            tieneExepcion=false;
            try
            {
                scanner.nextLine();
                System.out.println("\nTu oro es: " + dinero);
                System.out.println("Escriba el nombre del objeto que desea comprar tal y como apareció en pantalla");
                System.out.println("Presione 0 para no comprar nada");
                String seleccion = scanner.nextLine();

                if(seleccion.equals("0"))
                {
                    return null;
                }

                dato.validarCaracter(seleccion);
                dato.existeObjetoEnTienda(seleccion, objetos);

                if (objetos.containsKey(seleccion))
                {
                    dato.dineroInsuficiente(dinero, objetos.get(seleccion).getPrecio());

                    if (objetos.get(seleccion).isEsActivo())
                    {
                        System.out.println("\nCuantos elementos desea comprar");
                        int a = scanner.nextInt();
                        dato.validarCantidades(a);
                        objetos.get(seleccion).setCantidad(a);
                        dato.dineroInsuficiente(dinero, (objetos.get(seleccion).getPrecio() * a));

                        if (objetos.get(seleccion).getPrecio() * a <= dinero) {
                            dineroGastado = dinero - (objetos.get(seleccion).getPrecio() * a);
                            return objetos.get(seleccion);
                        } else {
                            objetos.get(seleccion).setCantidad(0);
                        }
                    }
                    else
                    {
                        if (objetos.get(seleccion).getPrecio() <= dinero)
                        {
                            dineroGastado = dinero - objetos.get(seleccion).getPrecio();
                            objetos.get(seleccion).setCantidad(0);

                            return objetos.remove(seleccion);
                        }
                    }


                }

            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError: Escriba un numero valido por favor");
                scanner.nextLine();
                tieneExepcion=true;
            }
            catch (NoExisteObjetoException | DineroInsuficienteException | CaracterInvalidoExcepcion |
                   CantidadInvalidaExcepcion e)
            {
                System.out.println("\nError: " + e.getMessage());
                tieneExepcion=true;
            }
        }
        while (tieneExepcion);

        return null;
    }

    public int getDineroGastado()
    {
        return dineroGastado;
    }



}
