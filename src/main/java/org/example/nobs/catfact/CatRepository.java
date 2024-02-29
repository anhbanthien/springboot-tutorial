package org.example.nobs.catfact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<CatfactEntity,Integer> {
}
