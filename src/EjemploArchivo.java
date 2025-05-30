/*Programa que lea la descripción y la cantidad(precio), y guardarlo en un archivo, cada vez que se abra
se debe ingresar un nuevo presupuesto sin perder los anteriores

opciones que debe tener:
a)agregar un gasto
b)calcular el total gastado
c)borrar archivo y empezar uno nuevo*/

import java.util.*;
import java.io.*;

public class EjemploArchivo {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc=new Scanner(System.in);
        String ruta="c:/cosas/";
        String nombre="mis gastos.txt";
        //String desc;
        int n;
        File archivo=new File(ruta+nombre);


        if (archivo.exists()) {
            FileReader fr =new FileReader(archivo);
            BufferedReader br=new BufferedReader(fr);
            n= Integer.parseInt(br.readLine());
            System.out.println("Tus gastos son los siguientes:");
            for (int i = 0; i < n; i++) {
                System.out.println(n);
                break;
                //desc= String.valueOf(i);
            }
        } else{
            n=0;
            FileWriter fw=new FileWriter(archivo);
            BufferedWriter bw=new BufferedWriter(fw);
            //System.out.println("¿Qué compraste?");
            //desc=sc.nextLine();
            //bw.write(""+desc);
            //bw.newLine();

            System.out.println("¿Cuánto gastaste?");
            n=sc.nextInt();
            bw.write(""+n);
            bw.flush();

        }
    }
}
