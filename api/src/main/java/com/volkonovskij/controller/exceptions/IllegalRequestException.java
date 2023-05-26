package com.volkonovskij.controller.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Setter
@Getter
public class IllegalRequestException extends RuntimeException{

    private BindingResult bindingResult;

    public IllegalRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
