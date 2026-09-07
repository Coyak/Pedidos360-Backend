package com.pedidos360.catalogo.controller;

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
        response.put("version", "1.0.0");
        return response;
    }

    @PostMapping("/productos")
    public Map<String, String> crearProducto(@RequestBody(required = false) String producto) {
        Map<String, String> response = new HashMap<>();
        
        // BUG INTENCIONAL (Versión 1.1.0):
        // Se intenta procesar 'producto' llamando a .toUpperCase() sin validar si es nulo,
        // lo cual causará un NullPointerException si la petición viene vacía o nula.
        String productoProcesado = producto.toUpperCase();

        response.put("status", "CREATED");
        response.put("producto", productoProcesado);
        return response;
    }
}
