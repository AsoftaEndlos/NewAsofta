package com.endlos.admin.machine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.endlos.admin.machine.model.MachineModel;

@Repository
public interface MachineRepository extends JpaRepository<MachineModel, Long>{

		@Query("select m from MachineModel m where m.status = false")
		public List<MachineModel> findByStatusdeactive();
		@Query("select m from MachineModel m where m.status = true")
		public List<MachineModel> findByStatusactive();


}
