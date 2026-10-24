package org.example.catalogomelodias.Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class Catalogo {
    private ArrayList<Melodia> canciones;

    public Catalogo() {
        this.canciones = new ArrayList<>();
    }

    public boolean registrarMelodia(Melodia obj) {
        boolean duplicada = false;
        if (canciones != null) {
            for (Melodia melodia : canciones) {
                if (melodia.equals(obj)) {
                    duplicada = true;
                    break;
                }
            }
        }
        if (!duplicada) {
            canciones.add(obj);
            return true; // Éxito al registrar.
        }
        return false; // Ya existía.
    }

    public boolean crearObjetoMelodia(String nombre, String compositor, int anio, String genero, String cantante, double precio, String formato) {
        return registrarMelodia(new Melodia(nombre, compositor, anio, genero, cantante, precio, formato));
    }

    // MUESTRAS LAS MELODÍAS DEL CATÁLOGO.
    public String mostrarTodasLasMelodias() {
        if (canciones.isEmpty()) return "El catálogo está vacío.";
        StringBuilder sb = new StringBuilder();
        for (Melodia melodia : canciones) {
            sb.append(melodia);
            sb.append("\n-----------------------------------\n");
        }
        return sb.toString();
    }

    // --- ACCIONES NECESARIAS ---

    // Eliminar melodía por nombre
    public boolean eliminarMelodiaPorNombre(String nombre) {
        return canciones.removeIf(m -> m.getNombre().equalsIgnoreCase(nombre));
    }

    // Buscar melodías por nombre
    public ArrayList<Melodia> buscarPorNombre(String nombre) {
        ArrayList<Melodia> encontradas = new ArrayList<>();
        for (Melodia m : canciones) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                encontradas.add(m);
            }
        }
        return encontradas;
    }

    // Buscar por ID para edición
    public Melodia buscarPorId(int id) {
        for (Melodia m : canciones) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // --- DETERMINACIONES Y ESTADÍSTICAS ---

    // Valor monetario de la colección
    public double calcularValorTotal() {
        double total = 0;
        for (Melodia m : canciones) {
            total += m.getPrecio();
        }
        return total;
    }

    // Obteniendo cantantes únicos
    public HashSet<String> obtenerCantantesUnicos() {
        HashSet<String> cantantes = new HashSet<>();
        for (Melodia m : canciones) {
            if (m.getCantante() != null && !m.getCantante().isEmpty()) {
                cantantes.add(m.getCantante());
            }
        }
        return cantantes;
    }

    // Géneros en la colección
    public HashSet<String> obtenerTodosLosGenerosIterativo() {
        HashSet<String> generos = new HashSet<>();
        for (Melodia cancion : canciones) {
            generos.add(cancion.getGenero());
        }
        return generos;
    }

    // Conteo de canciones por formato
    public Map<String, Integer> contarMelodiasPorFormato() {
        Map<String, Integer> mapaFormatos = new HashMap<>();
        for (Melodia m : canciones) {
            String formato = m.getFormato().toLowerCase();
            mapaFormatos.put(formato, mapaFormatos.getOrDefault(formato, 0) + 1);
        }
        return mapaFormatos;
    }

    // Filtrar por compositor
    public ArrayList<Melodia> melodiasPorCompositor(String compositor) {
        ArrayList<Melodia> resultado = new ArrayList<>();
        if (compositor == null) return resultado;
        for (Melodia melodia : canciones) {
            if (melodia.getCompositor() != null && melodia.getCompositor().equalsIgnoreCase(compositor)) {
                resultado.add(melodia);
            }
        }
        return resultado;
    }

    // Filtrar por cantante
    public ArrayList<Melodia> melodiasPorCantante(String cantante) {
        ArrayList<Melodia> resultado = new ArrayList<>();
        if (cantante == null) return resultado;
        for (Melodia melodia : canciones) {
            if (melodia.getCantante() != null && melodia.getCantante().equalsIgnoreCase(cantante)) {
                resultado.add(melodia);
            }
        }
        return resultado;
    }

    // Filtrar por año de grabación
    public ArrayList<Melodia> melodiasPorAnio(int anio) {
        ArrayList<Melodia> resultado = new ArrayList<>();
        for (Melodia melodia : canciones) {
            if (melodia.getAnio() == anio) {
                resultado.add(melodia);
            }
        }
        return resultado;
    }

    // Filtrar por género específico
    public ArrayList<Melodia> melodiasPorGenero(String genero) {
        ArrayList<Melodia> resultado = new ArrayList<>();
        for (Melodia melodia : canciones) {
            if (melodia.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(melodia);
            }
        }
        return resultado;
    }

    // Qué melodías tiene un artista
    public ArrayList<Melodia> melodiasPorArtista(String artista) {
        ArrayList<Melodia> resultado = new ArrayList<>();
        for (Melodia m : canciones) {
            boolean esCantante = m.getCantante() != null && m.getCantante().equalsIgnoreCase(artista);
            boolean esCompositor = m.getCompositor() != null && m.getCompositor().equalsIgnoreCase(artista);
            if (esCantante || esCompositor) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    // Consultas ordenadas por diferentes llaves
    public ArrayList<Melodia> obtenerColeccionOrdenada(int opcion) {
        ArrayList<Melodia> copia = new ArrayList<>(canciones);
        switch (opcion) {
            case 1: // Por Nombre
                copia.sort(Comparator.comparing(Melodia::getNombre, String.CASE_INSENSITIVE_ORDER));
                break;
            case 2: // Por Año
                copia.sort(Comparator.comparingInt(Melodia::getAnio));
                break;
            case 3: // Por Cantante
                copia.sort(Comparator.comparing(Melodia::getCantante, String.CASE_INSENSITIVE_ORDER));
                break;
            case 4: // Por Precio
                copia.sort(Comparator.comparingDouble(Melodia::getPrecio));
                break;
        }
        return copia;
    }
}