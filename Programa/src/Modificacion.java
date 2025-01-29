import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().metodoPrincipal();
    }

    private void metodoPrincipal() {
        String[] productos = {"probando", "hola"};
        menu(productos);
    }

    private void menu(String[] productos) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("-----MENÚ-----");
            System.out.println("1. AÑADIR PRODUCTO.");
            System.out.println("2. MODIFICAR PRODUCTO (POR NOMBRE).");
            System.out.println("3. MOSTRAR LLISTA DE PRODUCTOS:");
            System.out.println("4. MOSTRAR RESUMEN");
            System.out.println("5. SALIR");
            System.out.println("--------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    productos = addProduct(productos);
                    repeticion(productos);
                    break;
                case 2:
                    modifyProduct(productos);
                    repeticion(productos);
                    break;
                case 3:
                    for (int i = 0; i < productos.length; i++) {
                        System.out.print(productos[i]);
                        System.out.print(",");
                        System.out.print(" ");
                        System.out.println();
                    }
                    break;
                case 4:
                    Map<String, Integer> contador = new HashMap<>();
                    for (String producto : productos) {
                        contador.put(producto, contador.getOrDefault(producto, 0) + 1);
                    }
                    for (Map.Entry<String, Integer> input : contador.entrySet()){
                        System.out.printf("%s      %d\n", input.getKey(), input.getValue());
                        System.out.println();
                    }
                    break;

                default:
                    System.out.println("ADIÓS");
                    opcion = 5;
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
}