package com.zshop.helper.utils.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * To maintain compatability with old system.
 * @author shirshsinha
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private StatusEnum status;

    @JsonIgnore
    private Object message;

    @JsonIgnore
    private List<ErrorResponse> errors;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse> errors) {
        this.errors = errors;
    }

    public void setError(ErrorResponse error) {
        if (error != null) {
            if (this.errors == null) {
                this.errors = new ArrayList<>();
            }
            this.errors.add(error);
        }
    }
}