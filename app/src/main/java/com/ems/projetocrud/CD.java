package com.ems.projetocrud;

import java.io.Serializable;


    public class CD implements Serializable {
        private int id;
        private String nome;
        private String artista;
        private String gravadora;
        private String ano;

        public CD() {

        }

        public CD(int id,String nome, String artista, String gravadora, String ano) {
            this.id = id;
            this.nome = nome;
            this.artista = artista;
            this.gravadora = gravadora;
            this.ano = ano;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getArtista() {
            return artista;
        }

        public void setArtista(String artista) {
            this.artista = artista;
        }

        public String getGravadora() {
            return gravadora;
        }

        public void setGravadora(String gravadora) {
            this.gravadora = gravadora;
        }

        public String getAno() {
            return ano;
        }

        public void setAno(String ano) {
            this.ano = ano;
        }

        @Override
        public String toString() {
            return nome;
        }


        public String getDados() {
            return "ID: " + id + "\n" +
                    "Nome " + nome + "\n" +
                    "Artista: " + artista + "\n" +
                    "Gravadora: " + gravadora + "\n" +
                    "Ano: " + ano;
        }
}
