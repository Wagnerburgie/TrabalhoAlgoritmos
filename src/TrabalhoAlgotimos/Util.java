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
                replace(".", "").replace("!", "").replace("?", "").
                replace(",", "").replace(";", "").replace("=", "").
                replace("§", "").replace("|", "").replace("+", "").
                replace("{", "").replace("}", "").replace("\n", "").
                replace("[", "").replace("]", "").replace("<", "").
                replace(">", "").replace("/", "").replace("@", "").
                replace("#", "").replace("$", "").replace("%", "").
                replace("¨", "").replace("&", "").replace("*", "").
                replace("(", "").replace(")", "").replace("_", "").
                replace("¹", "").replace("²", "").replace("³", "").
                replace("£", "").replace("¢", "").replace("¬", "").
                replace("°", "").replace(":", "").replace("º", "");
        String resposta = null;
        if (!resultado.equals("")) {
            if (resultado.indexOf("'") == (resultado.length() - 1)) {
                resposta = resultado.replace("'", "");
                return resposta;
            } else if (resultado.indexOf("'") == 0) {
                for (int i = 1; i < resultado.length(); i++) {
                    resposta += resultado.charAt(i);
                }
                return resposta;
            }
        }
        return resultado;
    }
}
