/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesanuales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;


/**
 *
 * @author INE
 */
public class Encovi extends Documento{
    private List capitulos;
    Collator comparador = Collator.getInstance();
    private String rutaTex;
    private String rutaCSV;
    
    public Encovi(String titulo, String trimestre, String pYear, String rutaCSV, String rutaTex){
        super(titulo, trimestre, pYear);
        capitulos = new ArrayList();
        this.rutaCSV = rutaCSV;
        comparador.setStrength(Collator.PRIMARY);
        this.rutaTex=rutaTex;
        
    }
    public void generar(){
        
        for(int i =1;i<10;i++){
            generarDescripcion("1_0"+i);            
        }
        for(int i = 10;i<19;i++){
            generarDescripcion("1_"+i);
        }
        
        /*
        for(int i =1;i<10;i++){
            //generarDescripcion("1_0"+i);
            generarDescripcion("2_0"+i);
            //generarDescripcion("3_0"+i);
        }
        for(int i = 10;i<12;i++){
            generarDescripcion("2_"+i);
        }
        */
        /*
        for(int i =1;i<10;i++){
            generarDescripcion("3_0"+i);
        }
        for(int i = 10;i<31;i++){
            generarDescripcion("3_"+i);
        }
*/
    }
    
           

    private void generarDescripcion(String csv){
        File f = new File(getRuta(),csv + ".csv");
    BufferedReader br = null;
    String line = "";        
        String tituloSeccion="";
        String descripcionSeccion="";
        String tituloGrafica="";
        String subtituloGrafica="";
        boolean mapa=false;
        System.out.println("EL CSV ES " + csv);
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));
            int x=1;
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            switch(x){
                case 1:
                    tituloSeccion =line;
                    break;
                case 2:
                    tituloGrafica = line;
                    break;
                case 3:
                    subtituloGrafica = line;
                    break;
                case 4:
                    descripcionSeccion = line;
                    break;
                case 5:
                    System.out.println(line);
                    if (line.equalsIgnoreCase("m"))
                        mapa = true;
                    break;
                default:
                    break;
            }
            x++;
            }
        if (mapa){
            escribirLinea(cajotaMapa(csv, tituloSeccion, descripcionSeccion, tituloGrafica, subtituloGrafica,
                    "\\includegraphics[width=52\\cuadri]{graficas/"+csv+".pdf}",
                    "Instituto Nacional de Estadística",rutaTex), csv);
            System.out.println("ENTRE AL MAPA");
        }else
            escribirLinea(seccion(csv, tituloSeccion, descripcionSeccion, tituloGrafica, subtituloGrafica,
                    "\\begin{tikzpicture}[x=1pt,y=1pt]  \\input{graficas/"+csv+".tex}  \\end{tikzpicture}",
                    "Instituto Nacional de Estadística",rutaTex), csv);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }

    protected void escribirLinea(String linea, String csv) {
        FileWriter escritora;
        try {
            escritora = new FileWriter(rutaTex+"/encovi.tex",true);
            System.out.println(rutaTex+"/encovi.tex");
            BufferedWriter buffer = new BufferedWriter(escritora);
            buffer.write(linea);
            buffer.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
        
}
