package Juego;

import org.json.JSONException;
import org.json.JSONObject;

public class Objeto
{
    private String nombre;
    private final String stat;
    private final int precio;
    private final int cambioStat;
    private int cantidad;
    private boolean esActivo;
    private boolean esPasivo;


    public Objeto(String nombre, int precio, int cambioStat, String stat)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.cambioStat = cambioStat;
        this.stat = stat;
        this.esPasivo=false;
        this.esActivo=false;
        cantidad=0;
    }

    @Override
    public String toString()
    {
        return "\n" + nombre + '\n' + "Afecta a : " + stat + " aumentandolo en " + cambioStat + '\n' + "Precio: " + precio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getStat()
    {
        return stat;
    }

    public int getPrecio()
    {
        return precio;
    }

    public int getCambioStat()
    {
        return cambioStat;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public boolean isEsPasivo() {
        return esPasivo;
    }

    public void setEsPasivo(boolean esPasivo) {
        this.esPasivo = esPasivo;
    }

    public boolean isEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public void guarDarObjetoenJson(Objeto dato, String archivo)
{




}
public JSONObject toJson()
{
    JSONObject objeto=new JSONObject();
    try {
        objeto.put("nombre",this.nombre);
        objeto.put("stat",this.stat);
        objeto.put("precio",this.precio);
        objeto.put("cambioStat",this.cambioStat);
        objeto.put("cantidad",this.cantidad);
        objeto.put("EsActivo",this.esActivo);
        objeto.put("EsPasivo",this.esPasivo);
    }
    catch (JSONException e)
    {
        System.out.println("Error:"+e.getMessage());


    }

    return objeto;


}



}
