package br.com.kaikeventura.restmongodb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3200087792225255179L;

    @Getter
    public final String errorCode = "ERROR-11";
}
