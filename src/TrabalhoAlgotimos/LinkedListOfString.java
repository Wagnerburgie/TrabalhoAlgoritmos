package TrabalhoAlgotimos;

public class LinkedListOfString {

    // Classe Page (página).
    public class Page {
        // Número da página.
        public int numeroDaPagina;
        // Ocorrências de uma palavra na página.
        public int occorenciasNaPagina;

        public Page() {
        }

        public Page(int numero, int ocorrencias) {
            this.numeroDaPagina = numero;
            this.occorenciasNaPagina = ocorrencias;
        }
    }

    private class Node {

        public String element;
        public Node next;
        // Atributo página.
        public Page page;

        public Node(String element) {
            this.element = element;
            next = null;
            this.page = new Page();
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

    public void add(String element, int numeroPagina, int ocorrencias) {
        Node aux = new Node(element);
        aux.page.numeroDaPagina = numeroPagina;
        aux.page.occorenciasNaPagina = ocorrencias;
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
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

    public int getOccurrences(String element) {
        Node aux = head;
        for (int i = 1; i < count; i++) {
            if (aux.element.equalsIgnoreCase(element)) {
                return aux.page.occorenciasNaPagina;
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
            if (aux.element.equalsIgnoreCase(palavra)
                    && aux.page.numeroDaPagina == numero) {
                // Altera a ocorrência da palavra na página.
                aux.page.occorenciasNaPagina = ocorrencias;
                // Finaliza o método por não precisar 
                // continuar depois da alteração.
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

    public boolean containsPage(int numero) {
        Node aux = head;
        while (aux != null) {
            if (aux.page.numeroDaPagina == numero) {
                return (true);
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean containsElementAndPage(String element, int numero) {
        Node aux = head;
        while (aux != null) {
            if ((aux.element.equalsIgnoreCase(element))
                    && (aux.page.numeroDaPagina == numero)) {
                return (true);
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
            s.append("Número da Página: ");
            s.append(aux.page.numeroDaPagina);
            s.append("\n");
            s.append("Ocorrencias na Página: ");
            s.append(aux.page.occorenciasNaPagina);
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }
}
