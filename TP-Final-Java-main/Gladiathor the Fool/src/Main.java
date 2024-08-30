import Juego.Coliseo;

import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Coliseo juego = new Coliseo();
        boolean live=true;
        boolean winGame=false;

        while (live && !winGame)
        {
            live = juego.eventos();
            winGame = juego.getNumeroCombate() > 10;
        }

        if (!live)
        {
            System.out.println("\nHas sido derrotado");
        }
        else
        {
            System.out.println("Felicidades por pasar el juego profe!");
        }

        scanner.close();
    }


}