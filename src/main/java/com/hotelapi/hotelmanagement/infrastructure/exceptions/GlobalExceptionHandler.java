package com.hotelapi.hotelmanagement.infrastructure.exceptions;


import com.hotelapi.hotelmanagement.infrastructure.config.ErroApi;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroApi> tratarNaoEncontrado(
            EntityNotFoundException ex,
            HttpServletRequest request) {

        ErroApi erro = new ErroApi(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroApi> tratarRequisicaoInvalida(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        ErroApi erro = new ErroApi(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(erro);
    }



    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroApi> tratarConflitoDeRegra(
            IllegalStateException ex,
            HttpServletRequest request) {

        ErroApi erro = new ErroApi(
                HttpStatus.CONFLICT.value(),
                "Conflito de regra de negócio",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroApi> tratarErroGeral(
            Exception ex,
            HttpServletRequest request) {

        ErroApi erro = new ErroApi(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getRequestURI()
        );

        // só para log no console
        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}
