package com.hotelapi.hotelmanagement.infrastructure.config;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErroApi {

    private LocalDateTime dataHora;
    private int status;
    private String erro;
    private String mensagem;
    private String caminho;

    public ErroApi(int status, String erro, String mensagem, String caminho) {
        this.dataHora = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }


}
