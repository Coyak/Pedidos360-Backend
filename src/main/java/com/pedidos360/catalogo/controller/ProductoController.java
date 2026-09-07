package com.pedidos360.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Microservicio de Catálogo en ejecución");
        response.put("version", "1.1.1");
        return response;
    }

    @PostMapping("/productos")
    public ResponseEntity<Map<String, String>> crearProducto(@RequestBody(required = false) String producto) {
        Map<String, String> response = new HashMap<>();

        // FIX (Versión 1.1.1):
        // Se añade validación para evitar NullPointerException cuando 'producto' es nulo o está vacío.
        if (producto == null || producto.trim().isEmpty()) {
            response.put("status", "ERROR");
            response.put("message", "El nombre del producto es obligatorio y no puede estar vacío");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String productoProcesado = producto.trim().toUpperCase();

        response.put("status", "CREATED");
        response.put("producto", productoProcesado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
