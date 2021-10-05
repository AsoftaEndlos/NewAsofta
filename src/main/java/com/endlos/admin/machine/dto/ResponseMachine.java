package com.endlos.admin.machine.dto;

import com.endlos.admin.machine.model.MachineModel;
import com.endlos.admin.user.model.User;

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
