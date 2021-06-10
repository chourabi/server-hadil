package com.bsofts.fidelity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class SendingEmailException  extends  RuntimeException{
    public SendingEmailException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SendingEmailException(String exMessage) {
        super(exMessage);
    }

}
