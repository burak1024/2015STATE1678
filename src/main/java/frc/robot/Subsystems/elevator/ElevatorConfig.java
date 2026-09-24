package frc.robot.Subsystems.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ElevatorConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit=ElevatorConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicAcceleration=ElevatorConstants.ACCELERATION;

        config.MotionMagic.MotionMagicCruiseVelocity=ElevatorConstants.CRUISE_VELOCITY;

        config.MotionMagic.MotionMagicJerk = ElevatorConstants.JERK;

        config.Slot0.kS = ElevatorConstants.kS;

        config.Slot0.kV = ElevatorConstants.kV;

        config.Slot0.kA = ElevatorConstants.kA;

        config.Slot0.kP= ElevatorConstants.kP;

        config.Slot0.kI = ElevatorConstants.kI;

        config.Slot0.kD =ElevatorConstants.kD;

        return config;
    }
    
}
