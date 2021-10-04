package com.example.demo.file.Repository;

import com.example.demo.file.model.Masterdatamodel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MasterDataRepository extends JpaRepository<Masterdatamodel, Long> {
}
