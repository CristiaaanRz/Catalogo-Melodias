package org.example.catalogomelodias.Vista;

import org.example.catalogomelodias.Modelo.Genero;
import java.util.Scanner;

public class CatalogoView {
    private Scanner scanner = new Scanner(System.in);
    private String[] formatos = {"mp3", "ogg", "wav", "flac"};

    public int mostrarMenuPrincipal() {
        System.out.println("\n===== CATÁLOGO DE MELODÍAS =====");
        System.out.println("1. Registrar nueva melodía");
        System.out.println("2. Mostrar catálogo completo");
        System.out.println("3. Eliminar melodía (por nombre)");
        System.out.println("4. Conocer datos de una melodía");
        System.out.println("5. Corregir datos de una melodía");
        System.out.println("6. Consultas y Estadísticas de la colección");
        System.out.println("7. Consulta ordenada de la colección");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    public int mostrarSubmenuConsultas() {
        System.out.println("\n--- CONSULTAS Y ESTADÍSTICAS ---");
        System.out.println("1. Valor monetario de la colección");
        System.out.println("2. Cantantes en la colección (Total y Lista)");
        System.out.println("3. Géneros que abarca la colección");
        System.out.println("4. Melodías pertenecientes a un género");
        System.out.println("5. Cantidad de melodías por formato");
        System.out.println("6. Melodías compuestas por alguien en particular");
        System.out.println("7. Melodías cantadas por alguien en particular");
        System.out.println("8. Melodías grabadas en un año en particular");
        System.out.println("9. Todas las melodías asociadas a un artista (cantante/compositor)");
        System.out.println("10. Volver al menú principal");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    public int mostrarMenuOrdenamiento() {
        System.out.println("\n--- ORDENAR COLECCIÓN POR ---");
        System.out.println("1. Nombre de la melodía");
        System.out.println("2. Año de grabación");
        System.out.println("3. Nombre del cantante");
        System.out.println("4. Precio");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine().trim();
    }

    public int leerEntero() {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingresa un número entero válido: ");
            }
        }
    }

    public double leerDecimal() {
        while (true) {
            try {
                double num = Double.parseDouble(scanner.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingresa un valor numérico válido: ");
            }
        }
    }

    public String leerNombreMelodia() {
        return leerTexto("Ingrese el nombre de la melodía:");
    }

    public String leerCompositor() {
        return leerTexto("Ingrese el nombre del compositor:");
    }

    public String leerCantante() {
        return leerTexto("Ingrese el nombre del cantante:");
    }

    public int leerAnioMelodia() {
        System.out.print("Ingrese el año de grabación: ");
        return leerEntero();
    }

    public double leerPrecio() {
        System.out.print("Ingrese el precio pagado: $");
        return leerDecimal();
    }

    public String mostrarGenero() {
        Genero[] generos = Genero.values();
        System.out.println("Seleccione el género:");
        for (int i = 0; i < generos.length; i++) {
            System.out.println((i + 1) + ". " + generos[i]);
        }
        int seleccion;
        do {
            System.out.print("Opción: ");
            seleccion = leerEntero();
        } while (seleccion < 1 || seleccion > generos.length);

        return generos[seleccion - 1].toString();
    }

    public String mostrarFormato() {
        System.out.println("Seleccione el formato:");
        for (int i = 0; i < formatos.length; i++) {
            System.out.println((i + 1) + ". " + formatos[i]);
        }
        int seleccion;
        do {
            System.out.print("Opción: ");
            seleccion = leerEntero();
        } while (seleccion < 1 || seleccion > formatos.length);

        return formatos[seleccion - 1];
    }

    public void mostrarCadena(String mensaje) {
        System.out.println(mensaje);
    }
}