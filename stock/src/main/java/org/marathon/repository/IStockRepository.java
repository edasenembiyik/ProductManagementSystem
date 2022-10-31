package org.marathon.repository;


import org.marathon.repository.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends JpaRepository<Stock,Long> {

    Optional<Stock> findOptionalByProductId(Long productId);

    List<Stock> findByProductnameContainingIgnoreCase(String productname);
}
