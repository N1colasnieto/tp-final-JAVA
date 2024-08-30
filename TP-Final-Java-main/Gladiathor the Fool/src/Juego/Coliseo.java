package Juego;

import Criatura.*;
import ExcepcionesPersonalizadas.CaracterInvalidoExcepcion;
import ExcepcionesPersonalizadas.EleccionEquivocadaExcepcion;
import ExcepcionesPersonalizadas.MiAyudante;
import ExcepcionesPersonalizadas.NoExisteObjetoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Coliseo
{
    private final Tienda tiendita;
    private final ArrayList<String> monstruos;
    private final ArrayList<Personaje> jugador;
    private int numeroCombate;

    public Tienda getTiendita() {
        return tiendita;
    }

    public Coliseo()
    {
        tiendita = new Tienda();
        monstruos = new ArrayList<>();
        jugador = new ArrayList<>();
        this.numeroCombate = 1;
        llenarArrayMonstruos();
        llenarArrayJugador();
        cargarTienda();

    }

    public boolean eventos()
    {
        boolean win=true;
        Scanner scan = new Scanner(System.in);

        if(numeroCombate<10)
        {
            Ser sullivan = crearMonstruos();
            win = combate(sullivan);
        }

        if (win)
        {
            int oro = Personaje.getOro() + numeroCombate*10;
            Personaje.setOro(oro);

            System.out.println("Ganaste el combate n° " + numeroCombate + ", puedes descansar hasta tu proximo encuentro." + " Tu recompenza es de: " + numeroCombate*10 + " monedas de oro!.");
            System.out.println("\nTienes un total de: " + Personaje.getOro());

            numeroCombate++;
        }

        if(win && irATienda())
        {
            Objeto item;

            item=tiendita.menu(Personaje.getOro());

            if (item!=null)
            {
                if (item.getCantidad()!=0)
                {
                    Personaje.getMochila().agregarObjetoActivo(item);
                }
                else
                {
                    Personaje.getMochila().agregarObjetoPasivo(item, jugador);
                }

                Personaje.setOro(tiendita.getDineroGastado());
            }
            else
            {
                System.out.println("\nNo compraste nada!\nTulio les grita: Vuelvan cuando tengan dinero o sapen lo que quieren!\nEl siguiente combate empezará en breve");
            }

        }
        if(win)
        {
            System.out.println("Presiona cualquier Tecla para ir al siguiente combate...");
            scan.nextLine();
        }

        return win;
    }

    private Ser crearMonstruos()
    {
        Ser aux = new Ser(numeroCombate);
        aux.setNombre(monstruos.get(numeroCombate));
        return aux;
    }

    private void llenarArrayMonstruos()
    {
        monstruos.add("Easter Egg");
        monstruos.add("Cerbero");
        monstruos.add("Esqueleto ciclope");
        monstruos.add("Minotauro de Minos");
        monstruos.add("Arpia de la podredumbre");
        monstruos.add("Paladin desviado");
        monstruos.add("Dave");
        monstruos.add("Caballero sin cabeza");
        monstruos.add("Hombre lobo");
        monstruos.add("Dragon multicefalo");
        monstruos.add("Gladiathor");
    }

    private void llenarArrayJugador()
    {
        Pjugable guerrero = new Pjugable();
        guerrero.setGuerrero();

        Pjugable mago = new Pjugable();
        mago.setMago();

        Pjugable arquero = new Pjugable();
        arquero.setArquero();

        Pjugable clerigo = new Pjugable();
        clerigo.setClerigo();

        jugador.add(guerrero);
        jugador.add(arquero);
        jugador.add(clerigo);
        jugador.add(mago);
    }

    private boolean irATienda()
    {
        Scanner scan=new Scanner(System.in);
        boolean bandera;
        MiAyudante ayudantin=new MiAyudante();
        boolean error;

        do
        {
            error=false;
            try
            {
                System.out.println("\nQuieres ir a la tienda de Tulio? Si o No?");
                String aux=scan.nextLine();

                ayudantin.validarCaracter(aux);
                ayudantin.validarRespuesta(aux);

                bandera = aux.equalsIgnoreCase("si");
            }
            catch (CaracterInvalidoExcepcion a)
            {
                System.out.println("Error:"+a.getMessage());
                error=true;
                bandera=false;
            }
            catch (EleccionEquivocadaExcepcion e)
            {
                System.out.println("Error:"+e.getMessage());
                error=true;
                bandera=false;
            }
        }
        while (error);

        return bandera;
    }

    private boolean combate(Ser sullivan)
    {
        MiAyudante ayudante=new MiAyudante();
        ArrayList<Ser> combatientes;
        int i=0;
        int j;
        int k=0;
        ArrayList<Integer> atk = new ArrayList<>();
        ArrayList<Integer> vida = new ArrayList<>();
        ArrayList<Integer> velocidad = new ArrayList<>();

        for (int g=0; g<jugador.size();g++)
        {
            atk.add(jugador.get(g).getAtk());
            vida.add(jugador.get(g).getVidaActual());
            velocidad.add(jugador.get(g).getVelocidad());
        }

        Scanner scanner= new Scanner(System.in);

        while(heroesVivos() && enemigosVivos(sullivan))
        {
            combatientes=turnos(sullivan);
            Random msj=new Random();
            boolean mensaje=msj.nextBoolean();

            if(mensaje)
            {
                System.out.println("\"" + mensajeRandom() + "\"");
            }
            System.out.println("\nSu rival es: " + sullivan.getNombre());
            System.out.println("Vida actual: " + sullivan.getVidaActual());
            System.out.println("\nHeroes:      " + jugador.get(0).getNombre() + " " + jugador.get(1).getNombre() + " " + jugador.get(2).getNombre() + " " + jugador.get(3).getNombre());
            System.out.println("Vida actual: " + jugador.get(0).getVidaActual() + "  " + jugador.get(1).getVidaActual() + "      " + jugador.get(2).getVidaActual() + "     " + jugador.get(3).getVidaActual());
            System.out.println("atk:         " + jugador.get(0).getAtk() + "   " + jugador.get(1).getAtk() + "      " + jugador.get(2).getAtk() + "     " + jugador.get(3).getAtk());

            j=0;

            System.out.println("\nEs turno de: " + combatientes.get(i).getNombre());

            if (combatientes.get(i).getNombre().equals(sullivan.getNombre())) //turno monstruo
            {
                int pos = getHeroeAtacado(combatientes.size() - 1); //-1 por el monstruo que siempre esta al final

                if(combatientes.size()==2)
                {
                    pos =0;
                    while(jugador.get(pos).getVidaActual()<=0)          //que pasa si la posicion que busca ya fue atacado y esta muerto? busca otra posicion
                    {
                        pos++;
                    }
                }
                else
                {
                    while(jugador.get(pos).getVidaActual()<=0)            //lo mismo con lo otro, busca otra posicion
                    {
                        pos=getHeroeAtacado(combatientes.size()-1);
                    }
                }

                boolean flag = acierto();

                if (flag && !jugador.get(pos).isDefender())
                {
                    int danio=jugador.get(pos).getVidaActual()-sullivan.getAtk();

                    jugador.get(pos).setVidaActual(danio);

                    if (jugador.get(pos).getVidaActual()>0)
                    {
                        System.out.println(sullivan.getNombre() + " ataco a " + jugador.get(pos).getNombre() + " y le infligio " + sullivan.getAtk() + " de daño");
                    }
                    else
                    {
                        System.out.println(sullivan.getNombre() + " asesino a " + jugador.get(pos).getNombre());
                        jugador.get(pos).setVidaActual(0);
                        i=69;
                    }
                }
                else
                {
                    if(jugador.get(pos).isDefender())
                    {
                        System.out.println(jugador.get(pos).getNombre()+ " se esta defendiendo!. " + sullivan.getNombre() + " falló el ataque!");
                    }
                    else
                    {
                        System.out.println(sullivan.getNombre() + " falló el ataque!");
                    }
                    jugador.get(pos).setDefender(false);
                }
            }
            else
            {
                while (!combatientes.get(i).getNombre().equals(jugador.get(j).getNombre()))
                {
                    j++;
                }

                int opciones=4;
                boolean tieneError;

                do
                {
                    tieneError=false;
                    System.out.println("\n¿Qué deseas hacer?");
                    System.out.println("1. atacar");
                    System.out.println("2. defender");
                    System.out.println("3. mochila");

                    try
                    {
                        int eleccion = scanner.nextInt();
                        ayudante.validarEleccion(eleccion,opciones);

                        switch (eleccion)
                        {
                            case 1:
                                if (acierto())
                                {
                                    int danio = sullivan.getVidaActual() - jugador.get(j).getAtk();

                                    sullivan.setVidaActual(danio);

                                    System.out.println("Le realizaste " + jugador.get(j).getAtk() + " de daño a " + sullivan.getNombre());
                                }
                                else
                                {
                                    System.out.println("Has fallado el ataque!");
                                }
                                break;

                            case 2:
                                jugador.get(j).setDefender(true);
                                System.out.println("El proximo golpe no me alcanzará!");
                                break;

                            case 3:
                                String elec;
                                boolean eleccionNoExiste;


                                if(Personaje.getMochila().hayObjetosActivos())
                                {
                                    Personaje.getMochila().mostrarObjetos();

                                    do
                                    {
                                        eleccionNoExiste=false;

                                        scanner.nextLine();

                                        System.out.println("\nque objeto deseas usar? escriba el nombre tal y como se muestra por pantalla");

                                        try
                                        {
                                            elec = scanner.nextLine();


                                            ayudante.existeObjetoEnInventario(elec, Personaje.getMochila().getObjetosActivos());
                                            if (elec.equalsIgnoreCase("Pocion Explosiva"))
                                            {
                                                Personaje.getMochila().usarObjetoActivo(elec, sullivan);
                                            }
                                            else
                                            {
                                                int a = 0;

                                                for (Personaje personaje : jugador)
                                                {
                                                    System.out.println("- " + personaje.getNombre());
                                                }

                                                System.out.println("\nEn que personaje quieres usar el objeto?");
                                                String personaje = scanner.next();
                                                ayudante.existeJugador(personaje);

                                                while (a < jugador.size() && !personaje.equalsIgnoreCase(jugador.get(a).getNombre()))
                                                {
                                                    a++;
                                                }

                                                Personaje.getMochila().usarObjetoActivo(elec, jugador.get(a));
                                            }
                                        }
                                        catch (NoExisteObjetoException e)
                                        {
                                            System.out.println("Error:"+e.getMessage());
                                            eleccionNoExiste=true;

                                        }
                                        catch (EleccionEquivocadaExcepcion e)
                                        {
                                            System.out.printf("Error: " + e.getMessage());
                                            eleccionNoExiste=true;
                                        }
                                    }
                                    while (eleccionNoExiste);
                                }
                                else
                                {
                                    System.out.println("Tu mochila esta vacia!!");
                                }
                                break;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Error: escriba un numero valido por favor");
                        tieneError=true;
                        scanner.nextLine();
                    }
                    catch (EleccionEquivocadaExcepcion e)
                    {
                        System.out.println("Error:" + e.getMessage());
                        tieneError=true;
                    }

                }
                while(tieneError);
            }

            i++;

            if (i>=combatientes.size())
            {
                i=0;

                for (Personaje personaje : jugador)
                {
                    personaje.setDefender(false);
                }
            }

            if (sullivan.getNombre().equals("Gladiathor") && enemigosVivos(sullivan))
            {
                k++;

                if (k>=4)
                {
                    int regeneracion = sullivan.getVidaActual()+100;
                    sullivan.setVidaActual(regeneracion);

                    System.out.println("\nSu desesperacion me fortalece!!");

                    k=0;
                }
            }
        }

        if (!enemigosVivos(sullivan))
        {
            for (int g=0; g<jugador.size();g++)
            {
                jugador.get(g).setAtk(atk.get(g));
                jugador.get(g).setVidaActual(vida.get(g));
                jugador.get(g).setVelocidad(velocidad.get(g));
            }
        }

        return !enemigosVivos(sullivan);
    }

    private boolean heroesVivos()
    {
        return jugador.get(0).getVidaActual() > 0 || jugador.get(1).getVidaActual() > 0 || jugador.get(2).getVidaActual() > 0 || jugador.get(3).getVidaActual() > 0;
    }

    private boolean enemigosVivos(Ser sullivan)
    {
        return sullivan.getVidaActual() > 0;
    }

    private ArrayList<Ser> turnos(Ser sullivan)
    {
        ArrayList<Ser> orden = new ArrayList<>();

        if(jugador.get(0).getVidaActual()>0)
        {
            orden.add(jugador.get(0));
        }

        if (jugador.get(1).getVidaActual()>0)
        {
            orden.add(jugador.get(1));
        }

        if (jugador.get(2).getVidaActual()>0)
        {
            orden.add(jugador.get(2));
        }

        if (jugador.get(3).getVidaActual()>0)
        {
            orden.add(jugador.get(3));
        }

        orden.add(sullivan);

        ord_selcc(orden, orden.size());

        return orden;
    }

    public int getNumeroCombate()
    {
        return numeroCombate;
    }

    private static int pos_mayor(ArrayList<Ser> a, int v, int pos)
    {
        int i=pos+1;
        ArrayList<Ser> m = new ArrayList<>();
        m.add(a.get(pos));
        int pm=pos;

        while(i<v)
        {
            if(a.get(i).getVelocidad()>m.get(0).getVelocidad())
            {
                m.remove(0);
                m.add(a.get(i));
                pm=i;
            }

            i++;
        }

        return pm;
    }

    private void ord_selcc(ArrayList<Ser> a, int v)
    {
        int i =0;
        int pm;

        while(i<v-1)
        {
            pm=pos_mayor(a,v,i);
            a.add(i, a.get(pm));
            a.remove(pm+1);
            i++;
        }
    }

    private int getHeroeAtacado(int heroesVivos)
    {
        Random aux= new Random();

        return aux.nextInt(heroesVivos);
    }

    private boolean acierto()
    {
        Random aux= new Random();
        int i = aux.nextInt(100);

        return i>20;
    }
    private String mensajeRandom()
    {
        Random a= new Random();
        String atrapasuenios;

        boolean i=a.nextBoolean();

        MensajeGenerico<Object> generico=new MensajeGenerico<>();

        if(i)
        {
            Integer numero=a.nextInt(1,8);
            atrapasuenios=generico.obtenerMensaje(numero);
        }
        else
        {
            int letra=a.nextInt(65,72);
            String letrita=Character.toString(letra);
            atrapasuenios=generico.obtenerMensaje(letrita);
        }
        return atrapasuenios;
    }
    public void cargarTienda()
    {
        String contenido = JsonObjeto.descargarObjetos(String.valueOf(NombreArchivo.ARCHIVO_OBJETOS));

        try {
            JSONArray jsonArray1 = new JSONArray(contenido);

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject objeto = jsonArray1.getJSONObject(i);
                Objeto elObjeto = new Objeto(objeto.getString("nombre"), objeto.getInt("precio"), objeto.getInt("cambioStat"), objeto.getString("stat"));
                elObjeto.setCantidad(objeto.getInt("cantidad"));
                elObjeto.setEsActivo(objeto.getBoolean("EsActivo"));
                elObjeto.setEsPasivo(objeto.getBoolean("EsPasivo"));
                if(elObjeto.isEsActivo()) {
                    elObjeto.setEsActivo(objeto.getBoolean("EsActivo"));
                }
                else
                {
                    elObjeto.setEsPasivo(objeto.getBoolean("EsPasivo"));
                }
                tiendita.agregarObjetos(elObjeto);
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }
    public void guardarTienda()
    {
        tiendita.agregar();
        JSONArray jasonArray=new JSONArray();

        for(Objeto datito:tiendita.getObjetos().values())
        {
            jasonArray.put(datito.toJson());

        }

        JsonObjeto.guardarObjetos(jasonArray,String.valueOf(NombreArchivo.ARCHIVO_OBJETOS));


    }



}
