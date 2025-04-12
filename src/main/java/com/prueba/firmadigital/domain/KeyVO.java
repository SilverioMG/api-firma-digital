package com.prueba.firmadigital.domain;

import org.apache.commons.lang3.StringUtils;

public record KeyVO(String value) {

    public KeyVO {
        validate(value);
    }

    private void validate(String value) {
        if(StringUtils.isBlank(value)) throw new DomainValidationException("Valor no válida para 'key': " + value);
    }
}
