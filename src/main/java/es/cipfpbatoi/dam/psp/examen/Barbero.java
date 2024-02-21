package es.cipfpbatoi.dam.psp.examen;

import java.util.concurrent.ThreadLocalRandom;

public class Barbero implements Runnable{
    //TODO Pasamos a esta clase como parámetro la barbería para que se pueda usar el método doHairCut() de esta
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true){
            try {
                /*
                TODO Creamos un nuevo cliente con el contenido que devuelve el método doHairCut() para poder guardar
                 este y poder usar su nombre en un futuro
                 */
                Cliente cliente = barberia.doHairCut();
                //TODO Como podemos ver, usamos el cliente para poder sacar su nombre y así saber a quien se le corta el pelo
                System.out.println(cliente + ": Le están cortando el pelo en estos instantes...");

                /*
                TODO Usaremos un sleep para que el barbero tarde entre unos 5 segundos y medio y 11 segundos y un cuarto,
                  esto lo hacemos así para que podamos posteriormente ver todos los estados que puede alcanzar el programa
                 */
                Thread.sleep(ThreadLocalRandom.current().nextInt(5500, 11250 + 1));
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}
