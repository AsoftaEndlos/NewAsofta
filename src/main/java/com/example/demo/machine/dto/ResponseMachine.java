package com.example.demo.machine.dto;

import com.example.demo.machine.model.MachineModel;
import com.example.demo.user.model.User;

public class ResponseMachine {
    User user;
    MachineModel machineModel;

    public ResponseMachine(User user, MachineModel machineModel) {
        this.user = user;
        this.machineModel = machineModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MachineModel getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(MachineModel machineModel) {
        this.machineModel = machineModel;
    }
}
