package com.example.ac2_mobile.util;

import com.google.gson.annotations.SerializedName;

    public class Endereco {
        @SerializedName("logradouro")
        private String logradouro;

        @SerializedName("complemento")
        private String complemento;

        @SerializedName("bairro")
        private String bairro;

        @SerializedName("localidade")
        private String localidade;

        @SerializedName("uf")
        private String uf;

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getLocalidade() {
            return localidade;
        }

        public void setLocalidade(String localidade) {
            this.localidade = localidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }
}
