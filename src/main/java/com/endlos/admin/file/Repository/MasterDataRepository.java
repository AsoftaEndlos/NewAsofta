package com.endlos.admin.file.Repository;

import com.endlos.admin.file.model.Masterdatamodel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MasterDataRepository extends JpaRepository<Masterdatamodel, Long> {
}
