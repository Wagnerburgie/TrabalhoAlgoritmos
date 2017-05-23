package TrabalhoAlgotimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String texto = in.next();
        LinkedListOfString alice = criaListaSemAsStopWords(texto);
        int menu = 0;
        while (menu != 15) {
            menu = in.nextInt();
            System.out.println("Menu \n1: Indice remessivo em ordem alfabetica\n"
                    + "2: Exibir o percentual de stopWords\n"
                    + "3: Encontrar a palavra mais frequente\n"
                    + "4: Digite a palavra\n"
                    + "5: Encontre a pagina com mais palavras");
            switch (menu) {
                case 1:
                    break;

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;

            }
        }
    }

    public static LinkedListOfString addStopWords() {
        try {
            // Cria uma lista encadeada.
            LinkedListOfString lista = new LinkedListOfString();
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
            // Captura uma excessão caso houver.
        } catch (Exception e) {
            // Imprime o erro da excessão.
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public static LinkedListOfString criaListaSemAsStopWords(String texto) {
        try {

            // Pega a lista com as palavras que não
            // devem conter no arquivo alice.txt.
            LinkedListOfString stopWords = addStopWords();
            // Cria a lista que irá conter todas as palavras do arquivo
            // alice.txt que não estão presentes na lista stopWords.
            LinkedListOfString alice = new LinkedListOfString();
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
                linha = arquivo.readLine().split(" |\\-");
                // Cria um for pra percorrer as Strings da linha.
                for (int i = 0; i < linha.length; i++) {
                    // Pega somente os caracteres necessários
                    // de cada String da linha.
                    String palavra = removeSomeCharacters(linha[i]);
                    // Testa se a palavra não está presente
                    // dentro da lista stopWords
                    if (!stopWords.containsElement(palavra)
                            && !palavra.equals("")) {
                        // Caso a palavra não esteja na stopWords,
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
            // Imprime a lista alice.
            System.out.println(alice.toString());
            // Captura uma excessão caso houver.
            return alice;
        } catch (Exception e) {
            // Imprime o erro da excessão.
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public static LinkedListOfString criaListaComAsStopWords() {
        try {
            // Pega a lista com as palavras que não
            // devem conter no arquivo alice.txt.
            LinkedListOfString stopWords = addStopWords();
            // Cria a lista que irá conter todas as palavras do arquivo
            // alice.txt que não estão presentes na lista stopWords.
            LinkedListOfString alice = new LinkedListOfString();
            // Pega o arquivo alice.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader("src/documentos/alice.txt"));
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
                linha = arquivo.readLine().split(" |\\-");
                // Cria um for pra percorrer as Strings da linha.
                for (int i = 0; i < linha.length; i++) {
                    // Pega somente os caracteres necessários
                    // de cada String da linha.
                    String palavra = removeSomeCharacters(linha[i]);
                    // Testa se a palavra não está presente
                    // dentro da lista stopWords
                    if (!stopWords.containsElement(palavra)
                            && !palavra.equals("")) {
                        // Caso a palavra não esteja na stopWords,
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
            // Imprime a lista alice.
            System.out.println(alice.toString());
            // Captura uma excessão caso houver.
            return alice;
        } catch (Exception e) {
            // Imprime o erro da excessão.
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    // Método que remove caracteres desnecessários.
    public static String removeSomeCharacters(String palavra) {
        // Coloca a palavra recebida por parâmetro dentro de uma String
        // com todos os caracteres desnecessários removidos.
        String resultado = palavra.replace("'", "").replace("ª", "").
                replace(".", "").replace("!", "").replace("?", "").
                replace(",", "").replace(";", "").replace("=", "").
                replace("§", "").replace("|", "").replace("+", "").
                replace("-", "").replace("{", "").replace("}", "").
                replace("[", "").replace("]", "").replace("<", "").
                replace(">", "").replace("/", "").replace("@", "").
                replace("#", "").replace("$", "").replace("%", "").
                replace("¨", "").replace("&", "").replace("*", "").
                replace("(", "").replace(")", "").replace("_", "").
                replace("¹", "").replace("²", "").replace("³", "").
                replace("£", "").replace("¢", "").replace("¬", "").
                replace("°", "").replace(":", "").replace("º", "").
                replace("\"", "").replace(" ", "").replace("\n", "");
        // Retorna o resultado.
        return resultado;
    }
}
