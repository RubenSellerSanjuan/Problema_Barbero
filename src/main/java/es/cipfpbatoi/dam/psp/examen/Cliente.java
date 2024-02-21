package es.cipfpbatoi.dam.psp.examen;

import java.util.concurrent.ThreadLocalRandom;

public class Cliente implements Runnable{
    //TODO Pasamos la barberia como parametro para posteriormente poder usar su metodo newClient() para añadir clientes
    private final Barberia barberia;
    //TODO Cantidad de clientes que entrará a la barbería
    private int cantidad;
    //TODO El nombre que va a tener un cliente al entrar a la barbería
    private final String nombre;

    public Cliente(Barberia barberia, int cantidad, String nombre) {
        this.barberia = barberia;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        /*
        TODO Realizamos un bucle para añadir clientes hasta que la cantidad especificada como parámetro sea la misma
         que el número de clientes creados
         */
        for (int i = 1; i <= cantidad; i++){
            //TODO Creamos un cliente con nombre Cliente más el valor que contenga en el momento i, usando esta variable como id
            Cliente cliente = new Cliente(barberia, cantidad, "Cliente " + i);

            //TODO Usaremos el toString() de cliente para indicar que el mismo cliente antes creado a llegado a la barbería
            System.out.println(cliente + ": Ha llegado a la barbería");
            //TODO Añadimos el cliente a la Queue de barbería mediante el metodo newClient()
            barberia.newClient(cliente);

            try {
                //TODO Realizamos una espera entre llegadas de clientes de entre 5 segundos y 10 segundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000 + 1));
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    //TODO Usamos el toString() simplemente para generar un nombre a cada cliente
    @Override
    public String toString() {
        return nombre;
    }
}
