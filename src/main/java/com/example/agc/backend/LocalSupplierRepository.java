package com.example.agc.backend;

import com.example.agc.backend.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalSupplierRepository extends JpaRepository<Supplier, Long> {
}
