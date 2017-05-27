package TrabalhoAlgotimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            LinkedListOfWord listaComStopWords;
            System.out.print("Digite o nome do arquivo para ser lido: ");
            String nome = in.next();
            String caminho = "src/documentos/";
            String arquivo = caminho + nome;
            LinkedListOfWord stopWords = addStopWords();
            LinkedListOfWord listaSemStopWords = criaListaSemAsStopWords(arquivo, stopWords);
            if (listaSemStopWords == null) {
                System.out.println("\nDesculpe. O arquivo que você digitou"
                        + " não existe no pacote 'documentos' do projeto.\n");
            } else {
                System.out.println("\nEstrutura encadeada criada com sucesso.\n");
                int menu = 0;
                while (menu != 6) {
                    System.out.println("Menu\n\nDigite:\n1. Para exibir todo o índice "
                            + "remessivo em ordem alfabética\n"
                            + "2. Para exibir o percentual de stopwords do texto\n"
                            + "3. Para encontrar a palavra mais frequente "
                            + "(com maior número de ocorrências)\n"
                            + "4. Para pesquisar palavra\n"
                            + "5. Para encontrar página complexa "
                            + "(que contém o maior número de palavras indexadas)\n"
                            + "6. Para sair do menu\n");
                    menu = in.nextInt();
                    switch (menu) {
                        case 1:
                            exibirTodoIndiceRemissivoEmOrdemAlfabetica(listaSemStopWords);
                            break;
                        case 2:
                            listaComStopWords = criaListaComAsStopWords(arquivo, stopWords);
                            exibirPercentualDeStopWords(listaComStopWords, listaSemStopWords);
                            break;
                        case 3:
                            encontrarPalavraMaisFrequente(listaSemStopWords);
                            break;
                        case 4:
                            System.out.print("\nDigite a palavra que você deseja pesquisar: ");
                            String palavra = in.next();
                            pesquisarPalavra(listaSemStopWords, palavra);
                            break;
                        case 5:
                            encontrarPaginaComplexa(listaSemStopWords, stopWords);
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Digite apenas as opções "
                                    + "que aparecem no menu.");
                            break;
                    }
                }
            }
        }
    }

    // Criando uma lista de stopwords.
    public static LinkedListOfWord addStopWords() {
        try {
            // Cria uma lista encadeada.
            LinkedListOfWord lista = new LinkedListOfWord();
            // Pega o arquivo stopwords.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader("src/documentos/stopwords.txt"));
            // Cria um contador pra percorrer as linhas.
            int contadorLinha = 1;
            // Cria um contador pra percorrer as páginas.
            int contadorPagina = 1;
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Muda de página a cada 40 linhas.
                if (contadorLinha > 40) {
                    // Aumenta o contador da página.
                    contadorPagina++;
                    // Contador da linha volta a ser 1
                    // para indicar primeira linha da nova página.
                    contadorLinha = 1;
                }
                // Pega toda a linha numa String e passa para a próxima linha.
                String linha = arquivo.readLine();
                // Adiciona a linha dentro da lista.
                lista.add(linha, contadorPagina);
                // Aumenta o contador da linha.
                contadorLinha++;
            }
            // Fecha o arquivo.
            arquivo.close();
            // Retorna a lista com as palavras que não 
            // devem conter no arquivo alice.txt.
            return lista;
        } catch (IOException e) {
            return null;
        }
    }

    // Lista sem as stopwords.
    public static LinkedListOfWord criaListaSemAsStopWords(String texto, LinkedListOfWord stopWords) {
        try {
            // Cria a lista que irá conter todas as palavras do arquivo
            // enviado por parâmetro que não estão presentes na lista stopWords.
            LinkedListOfWord alice = new LinkedListOfWord(texto);
            // Pega o arquivo alice.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader(texto));
            // Cria o array que irá conter todas as palavras
            // de cada linha do arquivo alice.txt.
            String[] linha;
            // Criando contador para indicar a linha.
            int contadorLinha = 1;
            // Criando contador para indicar a página.
            int contadorPagina = 1;
            // Criando ocorrencias dentro de uma página.
            int ocorrencias = 0;
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Muda de página a cada 40 linhas.
                if (contadorLinha > 40) {
                    contadorPagina++;
                    contadorLinha = 1;
                }
                // Transforma a linha num array de Strings.
                linha = arquivo.readLine().split(" |\\--");
                // Cria um for pra percorrer as Strings da linha.
                for (int i = 0; i < linha.length; i++) {
                    // Pega somente os caracteres necessários
                    // de cada String da linha.
                    String palavra = Util.removeSomeCharacters(linha[i]);
                    // Testa se a palavra não está presente
                    // dentro da lista stopWords
                    if (!stopWords.containsElement(palavra)
                            && !palavra.equals("")) {
                        // Caso a palavra não esteja na stopWords e 
                        // ainda não foi adicionada na lista,
                        // ela será adicionada a lista alice.
                        if (!alice.containsElement(palavra)) {
                            ocorrencias = alice.getOccurrences(palavra, contadorPagina);
                            ocorrencias++;
                            alice.add(palavra, contadorPagina);
                        } else {
                            ocorrencias = alice.getOccurrences(palavra, contadorPagina);
                            ocorrencias++;
                            alice.changeOccurrences(palavra, contadorPagina, ocorrencias);
                        }
                    }
                }
                // Aumenta contador.
                contadorLinha++;
            }
            // Fecha o arquivo.
            arquivo.close();
            return alice;
        } catch (IOException e) {
            return null;
        }
    }

    // Lista com as stopwords.
    public static LinkedListOfWord criaListaComAsStopWords(String texto, LinkedListOfWord stopWords) {
        try {
            // Cria a lista que irá conter todas as palavras do arquivo
            // enviado por parâmetro que não estão presentes na lista stopWords.
            LinkedListOfWord alice = new LinkedListOfWord(texto);
            // Pega o arquivo alice.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader(texto));
            // Cria o array que irá conter todas as palavras
            // de cada linha do arquivo alice.txt.
            String[] linha;
            // Criando contador para indicar a linha.
            int contadorLinha = 1;
            // Criando contador para indicar a página.
            int contadorPagina = 1;
            // Criando ocorrencias dentro de uma página.
            int ocorrencias = 0;
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Muda de página a cada 40 linhas.
                if (contadorLinha > 40) {
                    contadorPagina++;
                    contadorLinha = 1;
                }
                // Transforma a linha num array de Strings.
                linha = arquivo.readLine().split(" |\\--");
                // Cria um for pra percorrer as Strings da linha.
                for (int i = 0; i < linha.length; i++) {
                    // Pega somente os caracteres necessários
                    // de cada String da linha.
                    String palavra = Util.removeSomeCharacters(linha[i]);
                    // Testa se a palavra está presente
                    // dentro da lista stopWords
                    if (stopWords.containsElement(palavra)
                            && !palavra.equals("")) {
                        // Caso a palavra esteja na stopWords e 
                        // ainda não foi adicionada na lista,
                        // ela será adicionada a lista alice.
                        if (!alice.containsElement(palavra)) {
                            ocorrencias = alice.getOccurrences(palavra, contadorPagina);
                            ocorrencias++;
                            alice.add(palavra, contadorPagina);
                        } else {
                            ocorrencias = alice.getOccurrences(palavra, contadorPagina);
                            ocorrencias++;
                            alice.changeOccurrences(palavra, contadorPagina, ocorrencias);
                        }
                    }
                }
                // Aumenta contador.
                contadorLinha++;
            }
            // Fecha o arquivo.
            arquivo.close();
            return alice;
        } catch (IOException e) {
            return null;
        }
    }

    // Métodos sobre as questões propostas no trabalho.
    private static void exibirTodoIndiceRemissivoEmOrdemAlfabetica(LinkedListOfWord listaSemStopWords) {
        try {
            System.out.println(listaSemStopWords.mostraOrdenado());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());;
        }
    }

    private static void exibirPercentualDeStopWords(LinkedListOfWord listaComStopWords, LinkedListOfWord listaSemStopWords) {
        float totalOcorrenciasComStopWords = listaComStopWords.getTotalDeTodasAsOcorrencias();
        float totalOcorrenciasSemStopWords = listaSemStopWords.getTotalDeTodasAsOcorrencias();
        float totalOcorrenciasDePalavrasNoTexto
                = totalOcorrenciasComStopWords + totalOcorrenciasSemStopWords;
        float resultado = (totalOcorrenciasComStopWords * 100) / totalOcorrenciasDePalavrasNoTexto;
        System.out.println("\nPorcentagem: " + resultado + "%\n");
    }

    private static void encontrarPalavraMaisFrequente(LinkedListOfWord listaSemStopWords) {
        System.out.println("\nPalavra mais frequente: " +
                listaSemStopWords.getPalavraMaisFrequente() + "\n");
    }

    private static void pesquisarPalavra(LinkedListOfWord listaSemStopWords, String palavra) throws IOException {
        Scanner ler = new Scanner(System.in);
        LinkedListOfPage paginas = listaSemStopWords.getPages(palavra);
        if (paginas != null) {
            System.out.println("\nPalavra: " + palavra + "\n"
                    + "Páginas (Número da página(Num) , "
                    + "Ocorrências na Página(Oco)): { " + paginas + " }\n");
            System.out.print("\nEscolha o número da página que "
                    + "você deseja visualizar a palavra: ");
            int num = ler.nextInt();
            boolean achouPagina = false;
            for (int i = 0; i < paginas.size(); i++) {
                if (paginas.get(i).nroPagina == num) {
                    achouPagina = true;
                    i = paginas.size();
                }
            }
            if (!achouPagina) {
                System.out.println("\nDesculpe. O número da página que "
                        + "você digitou não corresponde aos números "
                        + "de páginas em que a palavra aparece.\n");
            } else {
                System.out.println(mostrarPaginaComPalavra(palavra, num, listaSemStopWords.getArquivo()));
            }
        } else {
            System.out.println("\nDesculpe. Essa palavra não foi "
                    + "encontrada na estrutura encadeada.\n");
        }
    }

    private static void encontrarPaginaComplexa(LinkedListOfWord listaSemStopWords, LinkedListOfWord stopWords) {
        System.out.println(listaSemStopWords.buscaPaginaMaisReferenciada(stopWords));
    }

    private static String mostrarPaginaComPalavra(String palavra, int numeroPagina, String nomeArquivo) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("\n");
            BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            String[] array;
            int contadorLinha = 1;
            int contadorPagina = 1;
            while (arquivo.ready()) {
                if (contadorLinha > 40) {
                    contadorPagina++;
                    contadorLinha = 1;
                }
                linha = arquivo.readLine();
                array = linha.split(" |\\--");
                if (contadorPagina == numeroPagina) {
                    String texto = "";
                    if (linha.contains(palavra)) {
                        boolean adicionou = false;
                        for (int i = 0; i < array.length; i++) {
                            String palavraAux =Util.removeSomeCharacters(array[i]);
                            if (palavra.equals(palavraAux)) {
                                texto = linha.replace(palavraAux, "[" + palavraAux + "]");
                                adicionou = true;
                                i = array.length;
                            }
                        }
                        if(!adicionou){
                            texto = linha;
                        }
                    } else {
                        texto = linha;
                    }
                    builder.append(texto);
                    builder.append("\n");
                }
                contadorLinha++;
            }
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
