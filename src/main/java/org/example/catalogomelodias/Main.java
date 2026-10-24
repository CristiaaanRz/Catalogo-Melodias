package org.example.catalogomelodias;

import org.example.catalogomelodias.Controlador.Controlador;
import org.example.catalogomelodias.Modelo.Catalogo;
import org.example.catalogomelodias.Vista.CatalogoView;

public class Main {
    public static void main(String[] args) {
        Catalogo modelo = new Catalogo();
        CatalogoView vista = new CatalogoView();
        Controlador controlador = new Controlador(modelo, vista);

        controlador.iniciar();
    }
}