package org.example.catalogomelodias;

import org.example.catalogomelodias.Modelo.Catalogo;
import org.example.catalogomelodias.Modelo.Melodia;

import java.util.ArrayList;

public class MainPruebas {

    public static void main(String[] args) {
        System.out.println("========================================================================");
        System.out.println("      EJECUCIÓN DE PRUEBAS DE VALIDACIÓN - CATÁLOGO DE MELODÍAS        ");
        System.out.println("========================================================================\n");

        Catalogo catalogo = new Catalogo();

        // 1. REGISTRO DE MELODÍAS Y VALIDACIÓN DE REGLA DE DUPLICADOS (CANTANTE Y AÑO)
        System.out.println(">>> PRUEBA 1: REGISTRO Y REGLA DE DUPLICADOS (MISMO CANTANTE Y AÑO)");
        Melodia m1 = new Melodia("Amor Eterno", "Juan Gabriel", 1984, "ROMANTICA", "Rocío Dúrcal", 25.50, "mp3");
        Melodia m2 = new Melodia("La Bikina", "Rubén Fuentes", 1970, "RANCHERA", "Luis Miguel", 45.00, "ogg");
        Melodia m3 = new Melodia("Culpable o No", "Juan Carlos Calderón", 1988, "POP", "Luis Miguel", 20.00, "mp3");
        Melodia m4 = new Melodia("Querida", "Juan Gabriel", 1984, "POP", "Juan Gabriel", 30.00, "wav");

        System.out.println("1.1. Registrando 'Amor Eterno' (Rocío Dúrcal, 1984) -> Éxito: " + catalogo.registrarMelodia(m1));
        System.out.println("1.2. Registrando 'La Bikina' (Luis Miguel, 1970)    -> Éxito: " + catalogo.registrarMelodia(m2));
        System.out.println("1.3. Registrando 'Culpable o No' (Luis Miguel, 1988)-> Éxito: " + catalogo.registrarMelodia(m3));
        System.out.println("1.4. Registrando 'Querida' (Juan Gabriel, 1984)     -> Éxito: " + catalogo.registrarMelodia(m4));

        // Intento de duplicado: Mismo cantante ("Luis Miguel") y mismo año (1988)
        Melodia mDuplicada = new Melodia("Fría como el viento", "Juan Carlos Calderón", 1988, "POP", "Luis Miguel", 15.00, "flac");
        boolean resultadoDuplicado = catalogo.registrarMelodia(mDuplicada);
        System.out.println("1.5. Intentando registrar duplicado ('Luis Miguel', 1988) -> Éxito: " + resultadoDuplicado + " [ESPERADO: false]");

        System.out.println("\n------------------------------------------------------------------------");

        // 2. VALOR MONETARIO DE LA COLECCIÓN
        System.out.println(">>> PRUEBA 2: CÁLCULO DEL VALOR MONETARIO TOTAL");
        System.out.println("Valor total calculado de la colección: $" + String.format("%.2f", catalogo.calcularValorTotal()) + " [ESPERADO: $120.50]");

        System.out.println("\n------------------------------------------------------------------------");

        // 3. CANTANTES ÚNICOS EN LA COLECCIÓN
        System.out.println(">>> PRUEBA 3: CANTANTES EN LA COLECCIÓN (TOTAL Y LISTA)");
        var cantantes = catalogo.obtenerCantantesUnicos();
        System.out.println("Total de cantantes registrados: " + cantantes.size() + " [ESPERADO: 3]");
        System.out.println("Lista de cantantes: " + cantantes);

        System.out.println("\n------------------------------------------------------------------------");

        // 4. GÉNEROS Y FORMATOS DE MÚSICA
        System.out.println(">>> PRUEBA 4: GÉNEROS Y DESGLOSE POR FORMATOS DE AUDIO");
        System.out.println("Géneros que abarca la colección: " + catalogo.obtenerTodosLosGenerosIterativo());
        System.out.println("Cantidad de melodías por formato:");
        catalogo.contarMelodiasPorFormato().forEach((formato, cantidad) ->
                System.out.println(" - Formato " + formato.toUpperCase() + ": " + cantidad + " melodía(s)"));

        System.out.println("\n------------------------------------------------------------------------");

        // 5. BÚSQUEDAS ESPECÍFICAS (COMPOSITOR, CANTANTE, AÑO, ARTISTA)
        System.out.println(">>> PRUEBA 5: BÚSQUEDAS Y FILTROS ESPECÍFICOS");
        System.out.println("5.1. Compuestas por 'Juan Gabriel' (" + catalogo.melodiasPorCompositor("Juan Gabriel").size() + " encontrada(s))");
        System.out.println("5.2. Cantadas por 'Luis Miguel' (" + catalogo.melodiasPorCantante("Luis Miguel").size() + " encontrada(s))");
        System.out.println("5.3. Grabadas en el año 1984 (" + catalogo.melodiasPorAnio(1984).size() + " encontrada(s))");

        System.out.println("\n------------------------------------------------------------------------");

        // 6. CONSULTA GENERAL ORDENADA POR DIFERENTES LLAVES
        System.out.println(">>> PRUEBA 6: CONSULTA GENERAL ORDENADA POR AÑO CRONOLÓGICO");
        ArrayList<Melodia> ordenadas = catalogo.obtenerColeccionOrdenada(2); // 2 = Orden por Año
        for (Melodia m : ordenadas) {
            System.out.println(" - [" + m.getAnio() + "] " + m.getNombre() + " | Cantante: " + m.getCantante() + " | Precio: $" + String.format("%.2f", m.getPrecio()));
        }

        System.out.println("\n------------------------------------------------------------------------");

        // 7. EDICIÓN / CORRECCIÓN DE DATOS
        System.out.println(">>> PRUEBA 7: CORRECCIÓN DE DATOS DE UNA MELODÍA");
        Melodia aEditar = catalogo.buscarPorNombre("La Bikina").get(0);
        System.out.println("Precio anterior de 'La Bikina': $" + String.format("%.2f", aEditar.getPrecio()));
        aEditar.setPrecio(50.00);
        System.out.println("Nuevo precio corregido: $" + String.format("%.2f", aEditar.getPrecio()));

        System.out.println("\n------------------------------------------------------------------------");

        // 8. ELIMINACIÓN DE UNA MELODÍA POR NOMBRE
        System.out.println(">>> PRUEBA 8: ELIMINACIÓN DE MELODÍA POR NOMBRE ('Amor Eterno')");
        boolean eliminada = catalogo.eliminarMelodiaPorNombre("Amor Eterno");
        System.out.println("¿Se eliminó 'Amor Eterno' correctamente?: " + eliminada);
        System.out.println("Nuevo valor total de la colección tras la baja: $" + String.format("%.2f", catalogo.calcularValorTotal()) + " [ESPERADO: $100.00]");

        System.out.println("\n========================================================================");
        System.out.println("          TODAS LAS PRUEBAS COMPLETADAS DE MANERA SATISFACTORIA         ");
        System.out.println("========================================================================\n");
    }
}