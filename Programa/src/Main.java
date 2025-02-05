import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().metodoPrincipal();
    }

    private void metodoPrincipal() {
        String[] productos = lecturaEnFichero();
        menu(productos);
    }

    private void menu(String[] productos) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 8) {
            System.out.println("-----MENÚ-----");
            System.out.println("1. AÑADIR PRODUCTO.");
            System.out.println("2. ELIMINAR UN PRODUCTO (POR NOMBRE).");
            System.out.println("3. ELIMINAR UN PRODUCTO (POR POSICIÓN).");
            System.out.println("4. MOSTRAR TODA LA LISTA DE PRODUCTOS.");
            System.out.println("5. MOSTRAR UN RESUMEN DE TODO EL INVENTARIO.");
            System.out.println("6. MODIFICAR PRODUCTO (POR NOMBRE).");
            System.out.println("7. SUSTITUIR UN PRODUCTO POR OTRO (POR POSICIÓN)");
            System.out.println("8. SALIR");
            System.out.println("--------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    productos = addProduct(productos);
                    repeticion(productos);
                    break;
                case 2:
                    productos = eliminarNombre(productos);
                    break;
                case 3:
                    productos = eliminarPosicion(productos);
                    break;
                case 4:
                    mostrarLista(productos);
                    break;
                case 5:
                    mostrarResumen(productos);
                    break;
                case 6:
                    modifyProduct(productos);
                    repeticion(productos);
                    break;
                case 7:
                    sustituirProduct(productos);
                    repeticion(productos);
                    break;
                default:
                    escribirEnFichero(productos);
                    System.out.println("ADIÓS");
                    opcion = 8;
                    break;
            }
        }
    }

    //función que imprime un array
    private void repeticion(String[] productos) {
        for (int i = 0; i < productos.length; i++) {
            System.out.println(productos[i]);
        }
    }

    private void espacioBlanco() {
        System.out.println("\n\n");
    }

    //FUNCIÓN QUE PIDE UN STRING
    private String pedirString() {
        String producto;
        try {
            Scanner sc = new Scanner(System.in);
            producto = sc.nextLine();
        } catch (Exception e) {
            System.err.println("NO HAS PUESTO UN PRODUCTO VÁLIDO");
            producto = null;
        }
        return producto;
    }

    //FUNCIÓN QUE PIDE UN ENTERO
    private int pedirInt() {
        int num;
        try {
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
        } catch (Exception e) {
            System.err.println("NO HAS PUESTO UN NÚMERO VÁLIDO");
            num = 0;
        }
        return num;
    }

    //FUNCIÓN PARA AÑADIR PRODUCTO
    private String[] addProduct(String[] productos) {
        //Diálogo
        System.out.println("ESCRIBE EL PRODUCTO QUE QUIERES AÑADIR:");
        String producto = pedirString();
        //Copiar array principal añadiendo un tamaño más
        productos = Arrays.copyOf(productos, productos.length + 1);
        //añadir como último producto lo que haya puesto el usuario
        producto = producto.toLowerCase();
        productos[productos.length - 1] = producto;

        return productos;
    }

    //FUNCIÓN PARA ELIMINAR UN PRODUCTO POR NOMBRE
    public String[] eliminarNombre(String[] array) {
        //Dialogo
        System.out.println("ESCRIBE EL NOMBRE DEL PRODUCTO QUE QUIERAS ELIMINAR: ");
        String nombre = pedirString();
        nombre = nombre.toLowerCase();

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(nombre)) {
                array = eliminar(array, i);
                return array;
            }
        }
        System.out.println("PRODUCTO NO ENCONTRADO");
        return array;
    }

    //FUNCIÓN CON DIÁLOGO PARA ELIMINAR UN PRODUCTO POR POSICIÓN
    public String[] eliminarPosicion(String[] array) {
        System.out.println("ESCRIBE LA POSICIÓN DEL PRODUCTO QUE QUIERAS ELIMINAR: ");
        int num = pedirInt();
        if (num < 0 || num >= array.length) {
            System.out.println("PRODUCTO NO ENCONTRADO");
        } else {
            array = eliminar(array, num);
        }
        return array;
    }

    //FUNCIÓN PARA ELIMINAR UN PRODUCTO DE LA LISTA POR POSICIÓN
    private String[] eliminar(String[] array, int posicion) {
        //codigo que elimina el producto
        String[] productos = new String[array.length - 1];
        if (posicion == 0){
            System.arraycopy(array,1,productos,0,array.length-1);
        }else if (posicion == array.length - 1){
            productos = Arrays.copyOf(array,array.length - 1);
        }else {
            System.arraycopy(array,0,productos,0,posicion);
            System.arraycopy(array,posicion+1,productos,posicion,array.length-posicion-1);
        }
        System.out.println("PRODUCTO ELIMINADO CON ÉXITO");
        return productos;
    }

    //FUNCIÓN PARA MOSTRAR TODA LA LISTA DE PRODUCTOS
    private void mostrarLista(String[] productos) {
       try{
           System.out.print("\nPRODUCTOS: ");
           for (int i = 0; i < productos.length - 1; i++) {
               System.out.print(productos[i] + ", ");
           }
           System.out.println(productos[productos.length - 1] + ".");
           espacioBlanco();
       }catch (Exception e){
           System.out.println("NO HAY PRODUCTOS.");
       }

    }

    //FUNCIÓN QUE MUESTRA EL RESUMEN DEL INVENTARIO, NOMBRE Y SU CANTIDAD
    private void mostrarResumen(String[] productos) {
        Map<String, Integer> contador = new HashMap<>();
        for (String producto : productos) {
            contador.put(producto, contador.getOrDefault(producto, 0) + 1);
        }
        for (Map.Entry<String, Integer> input : contador.entrySet()) {
            System.out.printf("%s: %d\n", input.getKey(), input.getValue());
        }
    }

    //FUNCIÓN PARA MODIFICAR PRODUCTO
    private String[] modifyProduct(String[] productos) {
        //Diálogo
        System.out.println("ESCRIBE EL PRODUCTO QUE QUIERES MODIFICAR:");
        String producto = pedirString();
        producto = producto.toLowerCase();
        int contador = 0;
        //recorremos el array en busca del producto
        for (int i = 0; i < productos.length; i++) {
            //En caso de que encontremos el producto
            if (productos[i].equals(producto)) {
                //Diálogo para modificar
                System.out.println("MODIFICAR A: ");
                String modificacion = pedirString();
                modificacion = modificacion.toLowerCase();
                //En caso de que la modificación que haya hecho sea igual al producto
                if (modificacion.equals(producto)) {
                    System.out.println("NO HAS HECHO NINGÚN CAMBIO.");
                    //Modificar el producto con éxito
                } else {
                    productos[i] = modificacion;
                    System.out.println("ÉXITO, SE HA MODIFICADO " + producto + " A " + modificacion);
                }
                //En caso de que no encontremos el producto
            } else {
                contador++;
            }
        }
        //Si contador es 0 significa que no ha encontrado ningún producto a ese nombre
        if (contador == 0) {
            System.out.println("NO SE HA ENCONTRADO NINGUN PRODUCTO CON EL NOMBRE " + producto);
        }
        return productos;
    }

    //FUNCIÓN QUE SUSTITUYE UN PRODUCTO POR OTRO POR POSICIÓN CON DIÁLOGO
    private void sustituirProduct(String[] productos) {
        System.out.println("ESCRIBE LA POSICIÓN DEL PRODUCTO QUE QUIERES CAMBIAR: ");
        int pos = pedirInt();
        System.out.println("ESCRIBE LA POSICIÓN DEL PRODUCTO POR EL QUE QUIERES CAMBIAR: ");
        int posDos = pedirInt();
        sustituir(productos, pos, posDos);
    }

    //FUNCIÓN QUE SUSTITUYE UN PRODUCTO POR OTRO POR POSICIÓN
    private void sustituir(String[] productos, int pos, int posDos) {
        if (pos < 0 || posDos < 0 || pos >= productos.length || posDos >= productos.length) {
            System.out.println("PRODUCTO NO ENCONTRADO");
        }else {
            String prod = productos[pos];
            productos[pos] = productos[posDos];
            productos[posDos] = prod;
        }
    }

    private void escribirEnFichero(String[] productos) {
        FicheroEscribir escribir = new FicheroEscribir();
        String nombreFichero = "productos.txt";

        //DATOS A ESCRIBIR

        escribir.arrayEnFichero(nombreFichero,productos,productos.length);

        System.out.println("PRODUCTOS GUARDADOS CORRECTAMENTE EN EL FICHERO "+nombreFichero);
    }

    private String[] lecturaEnFichero() {
        FicheroLectura lectura = new FicheroLectura();
        String nombreFichero = "productos.txt";

        //DATOS A LEER
        String[] productos = lectura.leerFichero(nombreFichero);
        return productos;
    }
}