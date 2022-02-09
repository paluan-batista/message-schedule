package br.com.message.schedule.domain.model.entity;

import br.com.message.schedule.exception.TypeNotFoundException;

import java.util.Arrays;

public enum Type {
    WHATSAPP("WHATSAPP"), SMS("SMS"), EMAIL("EMAIL"), PUSH("PUSH");

    private String propertie;

    private Type(String propertie) {
        this.propertie = propertie;
    }

    private String getPropertie() {
        return propertie;
    }

    public static Type find(String fromString) {
        return Arrays.asList(Type.values()).stream()
                .filter(type -> type.getPropertie().equals(fromString.toUpperCase())).findFirst()
                .orElseThrow(() -> new TypeNotFoundException(fromString));
    }
}

