package Juego;

public class MensajeGenerico<T>
{
    public MensajeGenerico() {
    }

    public String obtenerMensaje(T obj)
    {
        if(obj.equals(1))
        {
            return "Son mas malos que pisar caca descalzo";
        }
        else if(obj.equals(2))
        {
          return "RUUAAAAGHH";
        }
        else if(obj.equals(3))
        {
            return "No son rivales para mi!! Muajajaja";
        }
        else if(obj.equals(4))
        {
            return "Quizas puedan derrotarme pero jamas venceras a Gladiathor";
        }
        else if(obj.equals(5))
        {
            return "Yo no cai del cielo, subi del infierno!!";
        }
        else if(obj.equals(6))
        {
            return "Si se rinden prometo que sus muertes seran rapidas";
        }
        else if(obj.equals(7))
        {
            return "Denme su sangre!";
        }
        else if(obj.equals(8))
        {
            return "NO TIENEN ESCAPATORIA!!!";
        }
        else if(obj.equals("A"))
        {
            return "Si tienes que forzarlo no es tu talla";
        }
        else if(obj.equals("B"))
        {
            return "Dudar es fracasar camaradas";
        }
        else if(obj.equals("C"))
        {
            return "Y la perra seguia y seguia...";
        }
        else if(obj.equals("D"))
        {
            return "necesito curacion!";
        }
        else if(obj.equals("E"))
        {
            return "FIRE IN THE HOLE!";
        }
        else if(obj.equals("F"))
        {
            return "POR FRODO!";
        }
        else if(obj.equals("G"))
        {
            return "I FEEL LIKE I'M WALKING ON SUNSHINE";
        }
        else if(obj.equals("H"))
        {
            return "Mantengan la formacion!";
        }

       return "";
    }
}
