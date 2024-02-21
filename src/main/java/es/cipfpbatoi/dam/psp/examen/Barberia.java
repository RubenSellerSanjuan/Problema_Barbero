package es.cipfpbatoi.dam.psp.examen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
    //TODO Máximo número de clientes permitidos dentro de la barbería
    private static final int NUM_SILLAS = 5;

    //TODO Lista donde se guardarán los clientes
    Queue<Cliente> clientes = new LinkedList<>();
    Lock lock = new ReentrantLock();
    //TODO Crearemos dos condiciones, una para los clientes y otra para el barbero
    Condition conditionBarbero = lock.newCondition();
    Condition conditionCliente = lock.newCondition();

    //TODO Metodo que introduce un cliente dentro de la barbería
    public void newClient(Cliente cliente){
        //TODO Bloquearemos el lock
        lock.lock();

        try {
            /*
            TODO Mientras la barbería este llena indicaremos cada vez que un cliente quiera entrar que no puede de
             momento y le haremos esperar con el condition.await de su condition correspondiente
             */
            while (barberiaLlena()){
                System.out.println("No pueden entrar más clientes a la barbería por el momento...");
                conditionCliente.await();
            }

            /*
            TODO Si la barberia no esta llena, guardamos dentro de la Queue el cliente pasado como parametro al metodo,
             en la ultima posicion de toda la LinkedList
             */
            clientes.add(cliente);
            //TODO Le lanzaremos al barbero una señal para que sepa que debe despertar y realizar su tarea mediante su condition
            conditionBarbero.signal();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            //TODO Desbloqueamos el lock
            lock.unlock();
        }
    }

    public Cliente doHairCut(){
        //TODO Bloquearemos el lock
        lock.lock();

        try {
            /*
            TODO Crearemos un cliente vacio por el momento, para que si hay clientes en la lista, este se convierta
             en uno de ellos mediante un poll a la lista, pero si no se devolvera el cliente null (vacio)
             */
            Cliente cliente = null;

            /*
            TODO Mientras que la barberia este vacia el barbero duerme (se espera), esto es posible gracias al método
             await de su condition
             */
            while (sinClientes()){
                System.out.println("El barbero duerme hasta que llegue un nuevo cliente...");
                conditionBarbero.await();
            }

            //TODO Si hay algún cliente, sacaremos el cliente que este al principio de la lista con el método poll()
            if (!sinClientes()){
                cliente = clientes.poll();
            }

            /*
            TODO Indicamos a los clientes con un signal que pueden continuar entrando ya que se ha vaciado una posición
             de la lista
             */
            conditionCliente.signal();
            /*
            TODO Finalmente, retornamos el cliente para poder mostrar su nombre posteriormente por pantalla desde la
             clase barbero
             */
            return cliente;
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            //TODO Desbloqueamos el lock
            lock.unlock();
        }
    }

    //TODO Metodo usado para comprobar si la lista de clientes está vacía
    private boolean sinClientes(){
        return clientes.isEmpty();
    }

    //TODO Metodo usado para comprobar si la lista de clientes está llena
    private boolean barberiaLlena(){
        return clientes.size() == NUM_SILLAS;
    }
}
