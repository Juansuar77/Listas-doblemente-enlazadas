
package umg.edu.progra.listas.doblementeEnlazada;

class Nodo {
    int data;
    Nodo next;
    Nodo prev;

    public Nodo(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoblementeEnlazada {

    private Nodo head;
    private Nodo tail;

    public DoblementeEnlazada() {
        this.head = null;
        this.tail = null;
    }

    public void insertAtEnd(int data) {
        Nodo newNode = new Nodo(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void insertAtBeginning(int data) {
        Nodo newNode = new Nodo(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public boolean delete(int data) {
        Nodo current = head;
        while (current != null) {
            if (current.data == data) {
                if (current == head) {
                    head = current.next;
                    if (head != null)
                        head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null)
                        tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean search(int data) {
        Nodo current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void displayForward() {
        Nodo current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void displayBackward() {
        Nodo current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Método 1: Contar el número de nodos en la lista
    public int contarNodos() {
        int contador = 0;
        Nodo current = head;
        while (current != null) {
            contador++;
            current = current.next;
        }
        return contador;
    }

    // Método 2: Insertar un nodo después de un valor específico
    public void insertarDespuesDe(int valorObjetivo, int nuevoValor) {
        Nodo current = head;
        while (current != null) {
            if (current.data == valorObjetivo) {
                Nodo nuevoNodo = new Nodo(nuevoValor);
                nuevoNodo.next = current.next;
                nuevoNodo.prev = current;
                if (current.next != null) {
                    current.next.prev = nuevoNodo;
                } else {
                    tail = nuevoNodo;
                }
                current.next = nuevoNodo;
                return;
            }
            current = current.next;
        }
        System.out.println("Valor objetivo no encontrado en la lista.");
    }

    // Método 3: Revertir la lista doblemente enlazada
    public void revertir() {
        Nodo actual = head;
        Nodo temp = null;

        while (actual != null) {
            temp = actual.prev;
            actual.prev = actual.next;
            actual.next = temp;
            actual = actual.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
    }

    // Clase principal para probar
    public static void main(String[] args) {
        DoblementeEnlazada lista = new DoblementeEnlazada();

        lista.insertAtEnd(10);
        lista.insertAtEnd(20);
        lista.insertAtEnd(30);
        lista.insertAtEnd(40);

        System.out.println("Lista original:");
        lista.displayForward();

        System.out.println("Cantidad de nodos: " + lista.contarNodos());

        System.out.println("Insertar 25 después de 20:");
        lista.insertarDespuesDe(20, 25);
        lista.displayForward();

        System.out.println("Lista revertida:");
        lista.revertir();
        lista.displayForward();
    }
}
