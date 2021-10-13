package com.endlos.admin.machine.controller;

import com.endlos.admin.exception.UserNotFound;
import com.endlos.admin.machine.service.MachineService;
import com.endlos.admin.user.Repository.UserRepository;
import com.endlos.admin.user.model.User;
import com.endlos.admin.machine.model.MachineModel;
import com.endlos.admin.machine.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/machine")
public class MachineController {

    @Autowired
    MachineService machineService;
    @Autowired
    MachineRepository machinerepository;
    @Autowired
    UserRepository userrepository;


    @PostMapping(value = "/create")
    public ResponseEntity<MachineModel> SaveMAchine(@Valid @RequestBody MachineModel machinemodel) throws Exception {
        return ResponseEntity.ok(machineService.MachineSave(machinemodel));
    }

    @GetMapping(value = "/{id}")
    public Optional<MachineModel> GetIdData(@PathVariable Long id) {
        return Optional.ofNullable(machineService.FindById(id).orElseThrow(() -> new UserNotFound("Sorry ! User Not Found " + id + " Number  id")));


    }

    @PutMapping("/{id}")
    public MachineModel UpdateData(@PathVariable Long id, @RequestBody MachineModel machinemodel) {
        return this.machineService.UpdateMachine(id, machinemodel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MachineModel> UpdateDatamachine(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Optional<MachineModel> ma = machinerepository.findById(id);
        if (ma.isPresent()) {
            fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(MachineModel.class, (String) key);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, ma.get(), value);
                    }
            );
            MachineModel machinesave = machinerepository.save(ma.get());

        }
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletedata(@PathVariable Long id) {

        try {
            machineService.machinemodeldelete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping(value = "/findall")
    public List<MachineModel> GetIdData() {
        List<MachineModel> machine = machineService.findAllData();
        return machine;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<?> FindByStatus() {
        return ResponseEntity.ok(machineService.findBystatusdeactive());

    }

    @PostMapping(value = "/findget")
    public ResponseEntity<?> FindByStatusw() {
        return ResponseEntity.ok(machineService.findBystatusactive());

    }

    @GetMapping(value = "/superfindbyid/{id}")
    public ResponseEntity<?> findBySuperUser(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(machineService.findBysuperuser(id));
    }

    @PutMapping("{suid}/Addsuperusermachine/{msuid}")
    User superuser(@PathVariable long suid, @PathVariable long msuid) {
        User user = userrepository.findById(suid).get();
        MachineModel machinemodel = machinerepository.findById(msuid).get();
        user.assignmachine(machinemodel);
        return userrepository.save(user);
    }

    //	@PutMapping ("/{mid}/Customermachine//{suid}")
//	MachineModel AssignCustomerToMachine (@PathVariable long mid, @PathVariable long 	suid)
//
//	{
//		User user= userrepository.findById(suid).get();
//		MachineModel model=machinerepository.findById(mid).get();
//		model.assignCustomer(user);
//		return machinerepository.save(model);
//
//	}
    @DeleteMapping("{suid}/deletesuperusermachine/{msuid}")
    public User deleteuser(@PathVariable long suid, @PathVariable long msuid) {
        User user = userrepository.findById(suid).get();
        MachineModel machinemodel = machinerepository.findById(msuid).get();
        user.Deletemachine(machinemodel);
        return userrepository.save(user);
    }

}
