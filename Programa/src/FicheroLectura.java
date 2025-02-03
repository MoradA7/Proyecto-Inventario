import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FicheroLectura {
    public String[] leerFichero(String nombreFichero) {
        String[] productos = new String[1];

        try(BufferedReader reader = new BufferedReader(new FileReader(nombreFichero))) {
            int e = 0;
            String linea;
            while ((linea = reader.readLine()) != null){
                productos[e] = linea;
                e++;
                productos = Arrays.copyOf(productos, productos.length + 1);
            }
            productos = Arrays.copyOf(productos, productos.length - 1);
            System.out.println("LECTURA DEL FICHERO COMPLETADA.");
        }catch (IOException e){
            System.out.println("NO SE PUEDE LEER EL FICHERO " + nombreFichero);
        }
        return productos;
    }
}
