// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public abstract class Subsystem extends SubsystemBase{
    public void stop(){}
    public void zeroSensors(){}
    public Boolean isReady(){return true;}

    public void emptyMethod(){};
    public Runnable activeStatePeriodic = ()->emptyMethod();
    
    public void readPeriodicInputs(){}
    public void writePeriodicOutputs(){}

    @Override
    public void periodic(){
        readPeriodicInputs();
        activeStatePeriodic.run();
        writePeriodicOutputs();
    }
}
