/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoAlgotimos;

public class Util {

    // Método que remove caracteres desnecessários.
    public static String removeSomeCharacters(String palavra) {
        // Coloca a palavra recebida por parâmetro dentro de uma String
        // com todos os caracteres desnecessários removidos.
        String resultado = palavra.replace("ª", "").replace("\"", "").
                replace("!", "").replace("?", "").replace(";", "").
                replace("=", "").replace("§", "").replace("|", "").
                replace("{", "").replace("}", "").replace("\n", "").
                replace("[", "").replace("]", "").replace("<", "").
                replace(">", "").replace("/", "").replace("@", "").
                replace("#", "").replace("$", "").replace("%", "").
                replace("¨", "").replace("&", "").replace("*", "").
                replace("(", "").replace(")", "").replace("_", "").
                replace("¹", "").replace("²", "").replace("³", "").
                replace("£", "").replace("¢", "").replace("¬", "").
                replace("°", "").replace(":", "").replace("º", "").
                replace("+", "");
                        
        String resposta = resultado.replace("'", " ");
        resultado = resposta.trim();
        resposta = resultado.replace(" ", "'");
        
        resultado = resposta.replace("-", " ");
        resposta = resultado.trim();
        resultado = resposta.replace(" ", "-");
        
        resposta = resultado.replace(".", " ");
        resultado = resposta.trim();
        resposta = resultado.replace(" ", ".");
        
        resultado = resposta.replace(",", " ");
        resposta = resultado.trim();
        resultado = resposta.replace(" ", ",");
        return resultado;
    }
}
