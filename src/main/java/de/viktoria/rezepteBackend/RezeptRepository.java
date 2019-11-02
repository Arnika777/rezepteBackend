package de.viktoria.rezepteBackend;

import org.springframework.data.jpa.repository.JpaRepository;

interface RezeptRepository extends JpaRepository<Rezept, Long> {

}
