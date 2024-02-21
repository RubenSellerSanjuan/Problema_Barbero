__PROBLEMA DEL BARBERO__
==========================

-------------------------
- _Decisión del Cliente_:
  Si quisieramos que el cliente pudiera elegir entre esperar, o irse,
  lo que podríamos hacer sería que el cliente tuviera un añadido de
  espera de tiempo máximo para ver si la barbería antes de que terminase
  este tiempo tiene un hueco libre, si fuera así, este cliente accedería
  a la barbería, pero si no, este cliente, con el tiempo de espera excedido
  se iría de la barbería. Para hacer que esto fuera posible, aparte
  indicaríamos mediante un mensaje si el cliente está esperando, y si el
  cliente se ha cansado de esperar y se ha ido.
-------------------------

--------------------------------
- _Manejo de la Cola de Espera_:
  La cola de espera en este ejercicio se está comprobando que sea eficiente
  en todo momento, puesto que usamos una Queue con un LinkedList, que permite
  que recojamos clientes de distintas posiciones, en este caso, como se añaden
  clientes en la posición final siempre mediante el método add() de LinkedList,
  y aparte, al sacar un cliente lo hacemos desde el inicio de la lista (su head)
  mediante el método poll(), siempre recogeremos como cliente a los que hayan
  llegado primero.
--------------------------------

----------------------------------
- _Concurrencia y Sincronización_:
  En este código usamos lock y condition, concretamente 2 de la última, para hacer
  posible la sincronización entre el barbero y los clientes. Por lo tanto, ahora en
  el caso de que quisiéramos evitar que un cliente despierte al barbero mientras este
  atiende a otro cliente, lo que podríamos usar sería una variable boolean que compruebe
  dicho estado, es decir, que compruebe si el barbero está despierto ya. Y en el caso de
  que varios clientes lleguen mientras solamente queda una silla disponible, esto lo
  podríamos arreglar recogiendo al primer cliente que llegue, y no permitiendo a más entrar,
  con lo cual también podríamos comprobarlo con una variable boolean, comprobaríamos que
  la barbería este llena (que el tamaño de la cola sea el mismo que el máximo de sillas).
----------------------------------

--------------------------
- _Justicia y Eficiencia_:
  En el diseño actual garantizamos que haya justicia al menos en que los clientes sean atendidos
  en el orden de llegada, pero en el caso de la atención al cliente no hay mucha justicia que
  digamos, para poder solventar esto podríamos usar varios hilos de barbero, con el fin de poder
  atender varios clientes al mismo tiempo, y que nunca tengan que esperar demasiado, mucho menos
  que no puedan entrar más clientes, por otro lado, también podríamos estabilizar un poco los
  tiempos de espera de clientes y barbero, para que el barbero no tarde demasiado en realizar su
  tarea, y los clientes no lleguen como si fueran palomitas en una sartén.
--------------------------