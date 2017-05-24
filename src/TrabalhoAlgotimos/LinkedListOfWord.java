package TrabalhoAlgotimos;

public class LinkedListOfWord {

    private class Node {

        public String element;
        public Node next;
        private LinkedListOfPage paginas;
        public int totalOcorrencias;

        public Node(String element) {
            this.element = element;
            totalOcorrencias = 1;
            next = null;
            paginas = new LinkedListOfPage();
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public LinkedListOfWord() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(Node nodo) {
        Node novo = nodo;
        if (head == null) {
            head = novo;
        } else {
            tail.next = novo;
        }
        tail = novo;
        count++;
    }

    public void add(String element, int numeroPagina) {
        Node novo = new Node(element);
        novo.paginas.add(numeroPagina);
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
        for (int i = 0; i < count; i++) {
            if (aux.element.equalsIgnoreCase(element)) {
                for (int j = 0; j < aux.paginas.size(); j++) {
                    if (aux.paginas.getNroPagina(j) == numeroDaPagina) {
                        return aux.paginas.getOcorrencia(j);
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
        for (int i = 0; i < count; i++) {
            // Testa se a palavra e o número da página
            // são os mesmos dos parâmetros.
            if (aux.element.equalsIgnoreCase(palavra)) {
                if (aux.paginas.aumentaOcorrencia(numero)) {
                    aux.totalOcorrencias++;
                    return;
                }
                aux.totalOcorrencias++;
                aux.paginas.add(numero);
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
                    if (aux.paginas.getNroPagina(j) == numero) {
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

    public String getPalavraMaisFrequente() {
        Node aux = head;
        int total = 0;
        String palavra = "";
        while (aux != null) {
            if (aux.totalOcorrencias > total) {
                total = aux.totalOcorrencias;
                palavra = aux.element;
            }
        }
        return palavra;
    }

    public Node getNodo(String palavra) {
        Node aux = head;
        while (aux != null && !aux.element.equalsIgnoreCase(palavra)) {
            aux = aux.next;
        }
        return aux;
    }

    public Node getNodo(int indice) {
        if (indice < 0 || indice > count) {
            return null;
        }
        Node aux = head;
        int contador = 0;
        while (aux != null && contador < indice) {
            aux = aux.next;
            contador++;
        }
        return aux;
    }

    public int getTotalDeTodasAsOcorrencias() {
        Node aux = head;
        int total = 0;
        while (aux != null) {
            total = total + aux.totalOcorrencias;
            aux = aux.next;
        }
        return total;
    }

    public LinkedListOfWord ordenarListaEmOrdemAlfabetica() {
        if (count == 0) {
            return null;
        }
        if (count == 1) {
            return this;
        }
        Node atual = head;
        Node proximo = head.next;
        Node aux = null;
        if (count == 2 && atual.element.compareTo(proximo.element) > 0) {
            aux = atual;
            atual = proximo;
            proximo = aux;
            proximo.next = atual;
            head = proximo;
            tail = atual;
            return this;
        } else if (count == 2 && atual.element.compareTo(proximo.element) <= 0) {
            return this;
        }
        return ordenaListaAuxiliar();
    }

    private LinkedListOfWord ordenaListaAuxiliar() {
        String[] palavras = new String[count];
        Node nodo1 = head;
        for (int i = 0; i < count; i++) {
            palavras[i] = nodo1.element;
            nodo1 = nodo1.next;
        }
        String aux = "";
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (palavras[j].compareTo(palavras[j + 1]) > 0) {
                    aux = palavras[j];
                    palavras[j] = palavras[j + 1];
                    palavras[j + 1] = aux;
                }
            }
        }
        LinkedListOfWord lista = new LinkedListOfWord();
        for (int x = 0; x < count; x++) {
            Node nodo2 = getNodo(palavras[x]);
            if (nodo2 != null) {
                lista.add(nodo2);
            }
        }
        head = null;
        tail = null;
        count = 0;
        for (int y = 0; y < lista.size(); y++) {
            add(lista.getNodo(y));
        }
        return this;
    }

    public void mostraOrdenado() {
        Node aux = head;
        LinkedListOfWord lista = new LinkedListOfWord();
        String maior = "";
        String adiciona = "";
        int gira = 0;
        while (gira < count) {
            aux = head;
            for(int i = 0; i < count; i++){
            adiciona = aux.element;
            aux = aux.next;
            if(lista.equals(adiciona)){
            }
            else{
            if(adiciona.charAt(0) < aux.element.charAt(0)){
            
            }
            }
            }
            gira++;
        }

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
            s.append("Páginas (Número da página , Ocorrências na Página): { ");
            s.append(aux.paginas);
            s.append(" }");
            s.append("\n");
            s.append("Total de ocorrências da palavra: " + aux.totalOcorrencias);
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }
}
