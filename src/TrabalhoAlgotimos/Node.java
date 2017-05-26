/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoAlgotimos;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Node {

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
}
