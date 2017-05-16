package TrabalhoAlgotimos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static LinkedListOfString addStopWords() {
        try {
            // Cria uma lista encadeada.
            LinkedListOfString lista = new LinkedListOfString();
            // Pega o arquivo stopwords.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader("src/documentos/stopwords.txt"));
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Pega toda a linha numa String e passa para a próxima linha.
                String linha = arquivo.readLine();
                // Adiciona a linha dentro da lista.
                lista.add(linha);
                // Imprime a linha que foi pega.
                System.out.println(linha);
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

    public static void main(String[] args) {
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
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Transforma a linha num array de Strings.
                linha = arquivo.readLine().split(" ");
                for (int i = 0; i < linha.length; i++) {
                    // Pega somente os caracteres necessários
                    // de cada String da linha.
                    String palavra = removeSomeCharacters(linha[i]);
                    // Testa se a palavra não está presente
                    // dentro da lista stopWords
                    if(!stopWords.contains(palavra)){
                        alice.add(palavra);
                    }
                    // Imprime a String sem os caracteres desnecessários.
                    System.out.println(palavra);
                }

            }
            // Fecha o arquivo.
            arquivo.close();
            
            // Captura uma excessão caso houver.
        } catch (Exception e) {
            // Imprime o erro da excessão.
            System.out.println("Erro: " + e.getMessage());
        }

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
                replace("\"","");
                
        // Retorna o resultado.
        return resultado;
    }
}
