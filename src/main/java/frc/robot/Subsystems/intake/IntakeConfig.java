package frc.robot.Subsystems.intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
public class IntakeConfig {
    public static TalonFXConfiguration Rollerconfig() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit = IntakeConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicJerk = IntakeConstants.JERK;

        config.MotionMagic.MotionMagicAcceleration = IntakeConstants.ACCELERATION;

        config.MotionMagic.MotionMagicCruiseVelocity = IntakeConstants.CRUISE_VELOCITY;

        config.Slot0.kS = IntakeConstants.kS;

        config.Slot0.kV = IntakeConstants.kV;

        config.Slot0.kA = IntakeConstants.kA;

        config.Slot0.kP = IntakeConstants.kP;

        config.Slot0.kI = IntakeConstants.kI;

        config.Slot0.kD = IntakeConstants.kD;

        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        return config;

    }

    public static TalonFXConfiguration reverseConfig() {

        TalonFXConfiguration config = Rollerconfig();

        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        return config;
    }
}
