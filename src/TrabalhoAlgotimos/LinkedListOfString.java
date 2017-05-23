package TrabalhoAlgotimos;

import java.util.ArrayList;

public class LinkedListOfString {
    
    private class Node {

        public String element;
        public Node next;
        private LinkedListOfInteger paginas;

        public Node(String element) {
            this.element = element;
            next = null;
            paginas = new LinkedListOfInteger();
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public LinkedListOfString() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(String element, int numeroPagina) {
        Node novo = new Node(element);
        novo.paginas.add(numeroPagina, 1);        
        if (head == null) {
            head = novo;
        } else {
            tail.next = novo;
        }
        tail = novo;
        count++;

    }

    public void add(int index, String element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node n = new Node(element);
        if (index == 0) { //insere no inicio
            n.next = head;
            head = n;
            if (tail == null) {
                tail = n;
            }
        } else if (index == count) { // insere no final
            tail.next = n;
            tail = n;
        } else { // insere no meio
            Node aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            n.next = aux.next;
            aux.next = n;
        }
        count++;
    }

    public String get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
    }

    public int getOccurrences(String element, int numeroDaPagina) {
        Node aux = head;
        for (int i = 1; i < count; i++) {
            if (aux.element.equalsIgnoreCase(element)) {
                for (int j = 0; j < aux.paginas.size(); j++) {
                    if (aux.paginas.get(j).numeroDaPagina == numeroDaPagina) {
                        return aux.paginas.get(j).occorenciasNaPagina;
                    }
                }
            }
            aux = aux.next;
        }
        return 0;
    }

    public void changeOccurrences(String palavra, int numero, int ocorrencias) {
        Node aux = head;
        // Percorre a lista de palavras.
        for (int i = 1; i < count; i++) {
            // Testa se a palavra e o número da página
            // são os mesmos dos parâmetros.
            if (aux.element.equalsIgnoreCase(palavra)) {
                for (int j = 0; j < aux.paginas.size(); j++) {
                    if (aux.paginas.get(j).numeroDaPagina == numero) {
                        // Altera a ocorrência da palavra na página.
                        aux.paginas.get(j).occorenciasNaPagina = ocorrencias;
                        // Finaliza o método por não precisar 
                        // continuar depois da alteração.
                        return;
                    }
                }
                aux.paginas.add(new Page(numero, ocorrencias));
                return;
            }
            aux = aux.next;
        }
    }

    public String set(int index, String element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        String tmp = aux.element;
        aux.element = element;
        return tmp;

    }

    public boolean remove(String element) {
        if (element == null) {
            return false;
        }
        if (count == 0) {
            return false;
        }

        if (head.element.equals(element)) { // remocao do primeiro
            head = head.next;
            if (count == 1) { // se havia so um elemento na lista
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head;
        Node aux = head.next;

        for (int i = 1; i < count; i++) {
            if (aux.element.equals(element)) {
                if (aux == tail) { // remocao do ultimo
                    tail = ant;
                    tail.next = null;
                } else { // remocao do meio
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            ant = ant.next;
            aux = aux.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public String removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        Node aux = head;
        if (index == 0) {
            if (tail == head) // se tiver apenas um elemento
            {
                tail = null;
            }
            head = head.next;
            count--;
            return aux.element;
        }
        int c = 0;
        while (c < index - 1) {
            aux = aux.next;
            c++;
        }
        String element = aux.next.element;
        if (tail == aux.next) {
            tail = aux;
        }
        aux.next = aux.next.next;
        count--;
        return element;
    }

    public int indexOf(String element) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(element)) {
                return (index);
            }
            aux = aux.next;
            index++;
        }
        return -1;
    }

    public boolean containsElement(String element) {
        Node aux = head;
        while (aux != null) {
            if (aux.element.equalsIgnoreCase(element)) {
                return (true);
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean containsElementAndPage(String element, int numero) {
        Node aux = head;
        while (aux != null) {
            if ((aux.element.equalsIgnoreCase(element))) {
                for (int j = 0; j < aux.paginas.size(); j++) {
                    if (aux.paginas.get(j).numeroDaPagina == numero) {
                        System.out.println("Existe a página " + numero 
                                + " para a palavra '" + aux.element + "'.");
                        return (true);
                    }
                }
            }
            aux = aux.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        while (aux != null) {
            s.append("\n");
            s.append("Palavra: ");
            s.append(aux.element);
            s.append("\n");
            s.append("Páginas (Número da página, Ocorrências na Página): { ");
            s.append("( ");
            s.append(aux.paginas.get(0).numeroDaPagina);
            s.append(" , ");
            s.append(aux.paginas.get(0).occorenciasNaPagina);
            s.append(" )");
            for (int i = 1; i < aux.paginas.size(); i++) {
                s.append(" , ");
                s.append("( ");
                s.append(aux.paginas.get(i).numeroDaPagina);
                s.append(" , ");
                s.append(aux.paginas.get(i).occorenciasNaPagina);
                s.append(" )");
            }
            s.append(" }");
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }
}
