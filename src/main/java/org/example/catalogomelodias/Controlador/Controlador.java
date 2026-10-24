package org.example.catalogomelodias.Controlador;

import org.example.catalogomelodias.Modelo.Catalogo;
import org.example.catalogomelodias.Modelo.Melodia;
import org.example.catalogomelodias.Vista.CatalogoView;

import java.util.ArrayList;

public class Controlador {
    private Catalogo modelo;
    private CatalogoView vista;

    public Controlador(Catalogo modelo, CatalogoView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion = 0;
        while (opcion != 8) {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    registrarMelodia();
                    break;
                case 2:
                    vista.mostrarCadena("\n=== TODAS LAS MELODÍAS ===");
                    vista.mostrarCadena(modelo.mostrarTodasLasMelodias());
                    break;
                case 3:
                    eliminarMelodia();
                    break;
                case 4:
                    buscarMelodia();
                    break;
                case 5:
                    corregirMelodia();
                    break;
                case 6:
                    gestionarConsultas();
                    break;
                case 7:
                    gestionarColeccionOrdenada();
                    break;
                case 8:
                    vista.mostrarCadena("\n¡Gracias por usar el Catálogo de Melodías!");
                    break;
                default:
                    vista.mostrarCadena("No válido.");
            }
        }
    }

    private void registrarMelodia() {
        vista.mostrarCadena("\n--- REGISTRAR NUEVA MELODÍA ---");
        String nm = vista.leerNombreMelodia();
        String co = vista.leerCompositor();
        int am = vista.leerAnioMelodia();
        String ge = vista.mostrarGenero();
        String ca = vista.leerCantante();
        double pr = vista.leerPrecio();
        String fo = vista.mostrarFormato();

        boolean exito = modelo.crearObjetoMelodia(nm, co, am, ge, ca, pr, fo);
        if (exito) {
            vista.mostrarCadena(" ¡Melodía registrada exitosamente!");
        } else {
            vista.mostrarCadena(" ERROR: La melodía ya existe en la colección.");
        }
    }

    private void eliminarMelodia() {
        String nombre = vista.leerTexto("Ingrese el nombre de la melodía a eliminar:");
        boolean eliminada = modelo.eliminarMelodiaPorNombre(nombre);
        if (eliminada) {
            vista.mostrarCadena(" Melodía con el nombre \"" + nombre + "\" eliminada correctamente.");
        } else {
            vista.mostrarCadena(" No se encontró ninguna melodía con ese nombre.");
        }
    }

    private void buscarMelodia() {
        String nombre = vista.leerTexto("Ingrese el nombre de la melodía a buscar:");
        ArrayList<Melodia> lista = modelo.buscarPorNombre(nombre);
        if (lista.isEmpty()) {
            vista.mostrarCadena(" No se encontró ninguna melodía registrada con ese nombre.");
        } else {
            vista.mostrarCadena("\n--- RESULTADOS DE BÚSQUEDA ---");
            for (Melodia m : lista) {
                vista.mostrarCadena(m.toString());
                vista.mostrarCadena("-----------------------------------");
            }
        }
    }

    private void corregirMelodia() {
        String nombre = vista.leerTexto("Ingrese el nombre de la melodía a editar:");
        ArrayList<Melodia> coincidencias = modelo.buscarPorNombre(nombre);

        if (coincidencias.isEmpty()) {
            vista.mostrarCadena(" No se encontraron melodías con ese nombre.");
            return;
        }

        Melodia seleccionada;
        if (coincidencias.size() == 1) {
            seleccionada = coincidencias.get(0);
        } else {
            vista.mostrarCadena("Se encontraron varias melodías con ese nombre:");
            for (Melodia m : coincidencias) {
                vista.mostrarCadena("ID: " + m.getId() + " | Cantante: " + m.getCantante() + " | Año: " + m.getAnio());
            }
            int id = vista.leerTexto("Ingrese el ID exacto de la melodía que desea corregir:").hashCode(); // O leer número directo
            seleccionada = modelo.buscarPorId(id);
        }

        if (seleccionada == null) {
            vista.mostrarCadena(" ID no encontrado.");
            return;
        }

        vista.mostrarCadena("\n--- EDITANDO DATOS DE: " + seleccionada.getNombre() + " ---");
        seleccionada.setNombre(vista.leerTexto("Nuevo nombre (" + seleccionada.getNombre() + "):"));
        seleccionada.setCompositor(vista.leerTexto("Nuevo compositor (" + seleccionada.getCompositor() + "):"));
        seleccionada.setAnio(vista.leerAnioMelodia());
        seleccionada.setGenero(vista.mostrarGenero());
        seleccionada.setCantante(vista.leerTexto("Nuevo cantante (" + seleccionada.getCantante() + "):"));
        seleccionada.setPrecio(vista.leerPrecio());
        seleccionada.setFormato(vista.mostrarFormato());

        vista.mostrarCadena(">> ¡Datos corregidos y actualizados con éxito!");
    }

    private void gestionarConsultas() {
        int subOpcion = 0;
        while (subOpcion != 10) {
            subOpcion = vista.mostrarSubmenuConsultas();
            switch (subOpcion) {
                case 1:
                    vista.mostrarCadena("\n Valor monetario total de la colección: $" + String.format("%.2f", modelo.calcularValorTotal()));
                    break;
                case 2:
                    var cantantes = modelo.obtenerCantantesUnicos();
                    vista.mostrarCadena("\n Total de cantantes únicos: " + cantantes.size());
                    vista.mostrarCadena("Lista de cantantes: " + cantantes);
                    break;
                case 3:
                    vista.mostrarCadena("\n Géneros musicales en la colección: " + modelo.obtenerTodosLosGenerosIterativo());
                    break;
                case 4:
                    String genero = vista.mostrarGenero();
                    mostrarListaMelodias(modelo.melodiasPorGenero(genero));
                    break;
                case 5:
                    vista.mostrarCadena("\n Melodías por formato de audio:");
                    modelo.contarMelodiasPorFormato().forEach((formato, cantidad) ->
                            vista.mostrarCadena(" - " + formato.toUpperCase() + ": " + cantidad + " melodía(s)"));
                    break;
                case 6:
                    String comp = vista.leerTexto("Nombre del compositor:");
                    mostrarListaMelodias(modelo.melodiasPorCompositor(comp));
                    break;
                case 7:
                    String cant = vista.leerTexto("Nombre del cantante:");
                    mostrarListaMelodias(modelo.melodiasPorCantante(cant));
                    break;
                case 8:
                    int anio = vista.leerAnioMelodia();
                    mostrarListaMelodias(modelo.melodiasPorAnio(anio));
                    break;
                case 9:
                    String art = vista.leerTexto("Nombre del artista (cantante o compositor):");
                    mostrarListaMelodias(modelo.melodiasPorArtista(art));
                    break;
                case 10:
                    break;
                default:
                    vista.mostrarCadena("Opción no válida.");
            }
        }
    }

    private void gestionarColeccionOrdenada() {
        int criterio = vista.mostrarMenuOrdenamiento();
        ArrayList<Melodia> listaOrdenada = modelo.obtenerColeccionOrdenada(criterio);
        mostrarListaMelodias(listaOrdenada);
    }

    private void mostrarListaMelodias(ArrayList<Melodia> lista) {
        if (lista.isEmpty()) {
            vista.mostrarCadena(" No se encontraron registros para esta consulta.");
        } else {
            vista.mostrarCadena("\n===================================");
            for (Melodia m : lista) {
                vista.mostrarCadena(m.toString());
                vista.mostrarCadena("-----------------------------------");
            }
        }
    }
}
