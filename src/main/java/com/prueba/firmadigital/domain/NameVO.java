package com.prueba.firmadigital.domain;

import org.apache.commons.lang3.StringUtils;

public record NameVO (String value) {

    public NameVO {
        validate(value);
        value = value.toLowerCase();
    }

    private void validate(String value) {
        if(StringUtils.isBlank(value)) throw new DomainValidationException("Valor no válida para 'name': " + value);
    }

}
