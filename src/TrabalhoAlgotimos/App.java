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
            BufferedReader arquivo
                    = new BufferedReader(new FileReader("src/documentos/alice.txt"));
            while (arquivo.ready()) {
                String linha = arquivo.readLine();
                System.out.println(linha);
            }
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

}
