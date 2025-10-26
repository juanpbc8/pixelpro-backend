package com.productos.ecommerce.spring_ecommerce.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrador")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AdministradorController {

    @GetMapping("/panel")
        public String panelAdmin(){
        return "Bienvenido al panel de administrador";

    }
}
