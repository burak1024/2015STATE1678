package frc.robot.Subsystems.arm;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ArmConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit = ArmConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicJerk = ArmConstants.JERK;

        config.MotionMagic.MotionMagicAcceleration = ArmConstants.ACCELERATION;

        config.MotionMagic.MotionMagicCruiseVelocity = ArmConstants.CRUISE_VELOCITY;

        config.Slot0.kS = ArmConstants.kS;

        config.Slot0.kV = ArmConstants.kV;

        config.Slot0.kA = ArmConstants.kA;

        config.Slot0.kP = ArmConstants.kP;

        config.Slot0.kI = ArmConstants.kI;

        config.Slot0.kD = ArmConstants.kD;

        return config;
    }
}
