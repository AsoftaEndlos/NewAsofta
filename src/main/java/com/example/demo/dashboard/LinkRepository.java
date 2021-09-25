package com.example.demo.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LinkRepository extends JpaRepository<LinkModel, Integer> {
//	@Query("select l from LinkModel l where l.lstatus0 = lstatus0 AND l.dstatus0 = dstatus0")
//	List<LinkModel> findByLstatusAnddstatusLinkModels(int lstatus0,int dstatus0 );
}
