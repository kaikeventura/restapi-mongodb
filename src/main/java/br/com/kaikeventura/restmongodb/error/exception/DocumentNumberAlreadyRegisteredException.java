package br.com.kaikeventura.restmongodb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class DocumentNumberAlreadyRegisteredException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6245021618789450803L;

    @Getter
    public final String errorCode = "ERROR-12";
}
