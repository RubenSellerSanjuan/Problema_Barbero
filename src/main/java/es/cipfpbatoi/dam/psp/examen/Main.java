package es.cipfpbatoi.dam.psp.examen;

public class Main {
    public static void main(String[] args){
        //TODO Instanciamos tanto barbería, como barbero, como cliente
        Barberia barberia = new Barberia();
        Barbero barbero = new Barbero(barberia);
        Cliente cliente = new Cliente(barberia, 50, "");

        //TODO Generamos dos nuevos hilos, que serán los de barbero y los de cliente
        Thread barberoThread = new Thread(barbero);
        Thread clienteThread = new Thread(cliente);

        //TODO Iniciamos los hilos
        barberoThread.start();
        clienteThread.start();
    }
}
