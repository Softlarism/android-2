package com.example.softlarism;

public class ComunidadItem {
        private String nombre;
        private String localizacion;
        private String miembros;

        public ComunidadItem(String nombre, String localizacion, String miembros) {
            this.nombre = nombre;
            this.localizacion = localizacion;
            this.miembros = miembros;
        }

        public String getNombre() { return nombre; }
        public String getLocalizacion() { return localizacion; }
        public String getMiembros() { return miembros; }
}
