package com.productos.ecommerce.spring_ecommerce.repositorio;

import com.productos.ecommerce.spring_ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


}
