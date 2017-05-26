package TrabalhoAlgotimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinkedListOfWord {

    private class Node {

        public String element;
        public Node next;
        public LinkedListOfPage paginas;
        public int totalOcorrencias;

        public Node(String element) {
            this.element = element;
            totalOcorrencias = 1;
            next = null;
            paginas = new LinkedListOfPage();
        }
        
        @Override
        public String toString(){
            StringBuilder s = new StringBuilder();
            s.append("\n");
            s.append("Palavra: ");
            s.append(element);
            s.append("\n");
            s.append("Páginas (Número da página(Num) , Ocorrências na Página(Oco)): { ");
            s.append(paginas);
            s.append(" }");
            s.append("\n");
            s.append("Total de ocorrências da palavra: ");
            s.append(totalOcorrencias);
            s.append("\n");
            return s.toString();
        }
    }

    private String arquivo;
    private Node head;
    private Node tail;
    private int count;

    // MÉTODOS DO ENSINADOS EM AULA SEM MODIFICAÇÕES.
    public LinkedListOfWord() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(String element) {
        Node novo = new Node(element);
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

    // Apenas o nome do método foi modificado, 
    // mas ele ele opera da mesma forma do que foi ensinado em aula.
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = head;
        while (aux != null) {
            s.append(aux.toString());
            aux = aux.next;
        }
        return s.toString();
    }

    // MÉTODOS CRIADOS E/OU MODIFICADOS PELO GRUPO.
    public LinkedListOfWord(String arquivo) {
        this.arquivo = arquivo;
        head = null;
        tail = null;
        count = 0;
    }

    public String getArquivo(){
        return this.arquivo;
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

    public void add(String element, int totalOcorrencias, LinkedListOfPage paginas) {
        Node novo = new Node(element);
        novo.paginas = paginas;
        novo.totalOcorrencias = totalOcorrencias;
        if (head == null) {
            head = novo;
        } else {
            tail.next = novo;
        }
        tail = novo;
        count++;
    }

    public Node get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return aux;
    }

    public Node get(String palavra) {
        Node aux = head;
        while (aux != null) {
            if (aux.element.equalsIgnoreCase(palavra)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }
    
    public LinkedListOfPage getPages(String palavra){
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(palavra)) {
                return aux.paginas;
            }
            aux = aux.next;
        }
        return null;
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
            aux = aux.next;
        }
        return palavra;
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

    public LinkedListOfWord mostraOrdenado() throws Exception {
        Node atual;
        int gira = 0;
        while (gira < count) {
            atual = head;
            while (atual != null && atual.next != null) {
                if (atual.element.compareTo(atual.next.element) > 0) {
                    modificarNodos(atual.next.element);
                }
                if (atual.next != null) {
                    atual = atual.next;
                }
            }
            gira++;
        }
        return this;
    }

    private boolean modificarNodos(String elemento) {
        if (isEmpty()) {
            throw new NullPointerException("Lista vazia!");
        }
        if (count == 1) {
            return true;
        }
        Node prev = head;
        Node atual = head.next;
        if (prev.element.equals(elemento)) {
            return false;
        }
        if (count == 2 && atual.element.equals(elemento)) {
            Node aux = atual;
            atual = prev;
            prev = aux;
            head = prev;
            tail = atual;
            head.next = tail;
            tail.next = null;
            return true;
        }
        if (atual.element.equals(elemento)) {
            Node aux = prev.next;
            Node helper = head;
            prev.next = prev.next.next;
            head = aux;
            head.next = helper;
            head.next.next = prev.next;
            return true;
        }
        return modificaNodosHelper(elemento);
    }

    public boolean modificaNodosHelper(String elemento) {
        Node prev = head;
        Node atual = head.next;
        Node proximo = head.next.next;
        while (proximo != null) {
            if (proximo.element.equals(elemento)) {
                Node aux = proximo;
                Node helper = atual;
                atual.next = atual.next.next;
                prev.next = aux;
                prev.next.next = helper;
                prev.next.next.next = atual.next;
                if (proximo.equals(tail)) {
                    tail = prev.next.next;
                }
                return true;
            }
            prev = prev.next;
            atual = atual.next;
            proximo = proximo.next;
        }
        return false;
    }

    public String buscaPaginaMaisReferenciada(LinkedListOfWord stopWords) {
        try {
            if (stopWords == null) {
                throw new NullPointerException("Lista de Stop Words está vazia.");
            }
            if (this.arquivo == null) {
                throw new NullPointerException("Não há arquivo na lista principal para ser lido.");
            }
            String resposta = "";
            LinkedListOfWord auxiliares = new LinkedListOfWord();
            BufferedReader arquivo
                    = new BufferedReader(new FileReader(this.arquivo));
            String[] linha;
            int contadorLinha = 1;
            int contadorPagina = 1;
            int referenciasPagina = 0;
            int contadorReferencia = 0;
            int numeroPagina = 0;
            while (arquivo.ready()) {
                if (contadorLinha > 40) {
                    contadorPagina++;
                    contadorLinha = 1;
                    if (contadorReferencia > referenciasPagina) {
                        referenciasPagina = contadorReferencia;
                        numeroPagina = contadorPagina-1;
                    }
                    contadorReferencia = 0;
                    auxiliares = new LinkedListOfWord();
                }
                linha = arquivo.readLine().split(" |\\-");
                for (int i = 0; i < linha.length; i++) {
                    String palavra = Util.removeSomeCharacters(linha[i]);
                    if (!palavra.equals("") && !stopWords.containsElement(palavra)
                            && !auxiliares.containsElement(palavra)) {
                        //int indice = indexOf(palavra);
                        Node helper = get(palavra);
                        if (helper != null) {
                            //Node helper = get(indice);
                            auxiliares.add(palavra);
                            for (int j = 0; j < helper.paginas.size(); j++) {
                                LinkedListOfPage.Page page = helper.paginas.get(j);
                                if (page.nroPagina == contadorPagina) {
                                    contadorReferencia += page.ocorrencia;
                                    j = helper.paginas.size();
                                }
                            }
                        }
                    }
                }
                contadorLinha++;
            }
            arquivo.close();
            if(contadorReferencia > referenciasPagina){
                referenciasPagina = contadorReferencia;
                numeroPagina = contadorPagina;
            }
            resposta = "Número da página: " + numeroPagina;
            resposta += "\nTotal de referências na página: " + referenciasPagina;
            return resposta;
        } catch (IOException | NullPointerException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
    
    
    
}
