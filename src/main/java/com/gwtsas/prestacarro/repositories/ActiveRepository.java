package com.gwtsas.prestacarro.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gwtsas.prestacarro.entities.Active;

public interface ActiveRepository extends JpaRepository<Active, Long> {

	@Query(value = 
			"SELECT a.* FROM (SELECT * FROM tbl_active a WHERE internal_code in :actives and is_disabled <> 1) a LEFT JOIN (SELECT * FROM tbl_loan where loan_registration_date BETWEEN date_sub( now(), INTERVAL 3 MONTH) and now() )p on p.id_active = a.id_active GROUP BY a.id_active ORDER BY COUNT(a.id_active) LIMIT 1;", nativeQuery = true)
	Optional<Active> getLeastUsedActive(List<String> actives);

}
