package com.endlos.admin.machine.repository;

import com.endlos.admin.machine.model.MachineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<MachineModel, Long> {

    @Query("select m from MachineModel m where m.status = false")
    List<MachineModel> findByStatusdeactive();

    @Query("select m from MachineModel m where m.status = true")
    List<MachineModel> findByStatusactive();


}
