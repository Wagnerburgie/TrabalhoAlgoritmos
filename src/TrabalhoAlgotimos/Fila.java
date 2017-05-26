package TrabalhoAlgotimos;

public class Fila {

    private Node head;
    private Node tail;
    private int count;

    public Fila () {
        clear();
    }
    
    public void clear () {
        this.head = this.tail = null;
        this.count = 0;
    }
    
    public int size () {
        return count;
    }
    
    public boolean isEmpty () {
        return (count == 0);
    }
    
    public Node head () throws Exception {
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }
        
        return head;
    }
    
    public Node percorreFila() throws Exception{
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }        
        Node aux = head();
        enqueue(aux);
        dequeue();
        return aux;
    }
    
    public void enqueue (String element) {
        Node newNode = new Node(element);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;        
    }
    
    public void enqueue (Node nodo) {
        if (isEmpty()) {
            head = nodo;
            tail = nodo;
        } else {
            tail.next = nodo;
            tail = nodo;
        }
        count++;
    }
    
    public Node dequeue () throws Exception {
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }        
        Node aux = head;
        
        if (head == tail) { // count == 1 ?
            head = null;
            tail = null;
        } else {    // Há mais de um elemento.
            head = head.next;
        }
        count--;
        return aux;
    }
}
