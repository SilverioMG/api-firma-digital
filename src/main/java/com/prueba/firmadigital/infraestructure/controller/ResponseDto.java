package com.prueba.firmadigital.infraestructure.controller;

public record ResponseDto<T>(boolean ok, String message, T data) {
}
