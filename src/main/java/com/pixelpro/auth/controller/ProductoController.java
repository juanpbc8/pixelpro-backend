package com.productos.ecommerce.spring_ecommerce.controller;



import com.productos.ecommerce.spring_ecommerce.model.Producto;
import com.productos.ecommerce.spring_ecommerce.model.Usuario;
import com.productos.ecommerce.spring_ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") //  permite que el frontend pueda acceder

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.findAll();
    }

    //Obtener producto por id

    @GetMapping("/editar/{id}")
    public ResponseEntity<Producto> editar(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.get(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarProducto(
            @PathVariable Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("precio") double precio
    ) {
        Optional<Producto> optionalProducto = productoService.get(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);
            productoService.save(producto);
            return ResponseEntity.ok("Producto actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }

    //Eliminar producto

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }


    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("precio") double precio
    ) {
        Usuario usuario = new Usuario();
        usuario.setId(1); // ID del usuario existente en la base de datos

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);
        producto.setUsuario(usuario);

        productoService.save(producto);

        return ResponseEntity.ok("Producto guardado correctamente");
    }

}
