/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesanuales;

/**
 *
 * @author carto
 */
public class ReportesAnuales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Encovi nuevo;
        
        nuevo = new Encovi("Encovi","", "2014", args[0],args[1]);
        nuevo.setRuta(args[0]);
        nuevo.setTex("encovi");
        nuevo.resetearLinea();
        nuevo.generar();
        // TODO code application logic here
    }
    
}
