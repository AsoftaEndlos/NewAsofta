package com.example.demo.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	@Autowired 
	DashBoardRepository dashboardrepository;
	
	
	public DashBoardModel MachineSave(DashBoardModel machinemodel) throws Exception
	{
		
		
	DashBoardModel machine=	dashboardrepository.save(machinemodel);
		if (machine== null) {
			throw new Exception("Machine Details are Empty");
			
		}
		return machine;
		
	 
	}
	public List<DashBoardModel> findall(DashBoardModel dash)
	{
		return dashboardrepository.findAll();
	}
}
