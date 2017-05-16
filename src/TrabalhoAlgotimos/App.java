package TrabalhoAlgotimos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        try {
            // Pega o arquivo alice.txt dentro do pacote documentos.
            BufferedReader arquivo
                    = new BufferedReader(new FileReader("src/documentos/alice.txt"));
            // Vai testando se tem linha no arquivo dentro de um while.
            while (arquivo.ready()) {
                // Pega toda a linha numa String e passa para a próxima linha.
                String linha = arquivo.readLine();
                // Imprime a linha que foi pega.
                System.out.println(linha);
            }
            // Fecha o arquivo.
            arquivo.close();
            // Captura uma excessão caso houver.
        } catch (Exception e) {
            // Imprime o erro da excessão.
            System.out.println("Erro: " + e.getMessage());
        }

    }

}
