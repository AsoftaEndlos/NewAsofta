package com.example.demo.machine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.machine.model.MachineModel;
import com.example.demo.machine.repository.MachineRepository;

@Service
public class MachineService {
	@Autowired
	MachineRepository machinerepositry;
	
	// this Method is Add machine data
	public MachineModel MachineSave(MachineModel machinemodel) throws Exception
	{
	MachineModel machine=	machinerepositry.save(machinemodel);
		if (machine== null) {
			throw new Exception("Machine Details are Empty");
			
		}
		return machine;
	 
	}
	// this Method is method is  find id through data in machine model
	public  Optional<MachineModel> FindById(Long id)
	{
		return machinerepositry.findById(id);
		
	}
	// this Method is machine update
	public MachineModel UpdateMachine(Long id,MachineModel machinemodel)
	{
		Optional<MachineModel> machine=machinerepositry.findById(id);
		if(machine.isPresent())
		{
			MachineModel machinesave=machine.get();
			machinesave.setLocation(machinemodel.getLocation());
			machinesave.setPassword(machinemodel.getPassword());
			machinesave.setCustomerpin(machinemodel.getCustomerpin());
			machinesave.setSuperuserpin(machinemodel.getSuperuserpin());
			//machinesave.setUser(machinemodel.getUser());
			 machinerepositry.save(machinesave);
		}
		return machinemodel;
	}
	public MachineModel UpdateMachineAllocation(Long id,MachineModel machinemodel)
	{
		Optional<MachineModel> machine=machinerepositry.findById(id);
		if(machine.isPresent())
		{
			MachineModel machinesave=machine.get();
			machinesave.setStatus(machinemodel.isStatus());
			machinesave.setUser(machinemodel.getUser());
			 machinerepositry.save(machinesave);
		}
		return machinemodel;
	}
	
	
	// this methid represent delete by id in machine
	public  void machinemodeldelete(Long id)
	{
		machinerepositry.deleteById(id);
		
	}
	public List<MachineModel> findAllData()
	{
		List<MachineModel> machineModels=machinerepositry.findAll();
		return  machineModels;
	}
	public List<MachineModel> findBystatusdeactive()
	{
		return machinerepositry.findByStatusdeactive();
	}
	public List<MachineModel> findBystatusactive()
	{
		return machinerepositry.findByStatusactive();
	}
	public Optional<MachineModel>  findBysuperuser(Long id) throws Exception
	{
		if( machinerepositry.findById(id).isPresent())
			return machinerepositry.findById(id);
		else 
			throw new Exception("Machine data Are Empty"); 
	}

	
}
