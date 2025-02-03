import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FicheroEscribir {

    //Funcion para escribir un producto
    private void escribirEnFichero(String nombreFichero, String nombreProducto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero, true))) {
            writer.write(nombreProducto);
            writer.newLine();
        }catch (IOException e){
            System.out.println("ERROR AL ESCRIBIR EN EL FICHERO " + nombreFichero);
        }
    }

    public void arrayEnFichero(String nombreFichero, String[] productos, int cant) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero, false))) {
            for (int i = 0; i < cant; i++) {
                escribirEnFichero(nombreFichero, productos[i]);
            }
        }catch (IOException e){
            System.out.println("ERROR AL ESCRIBIR EN EL FICHERO " + nombreFichero);
        }
    }

}
