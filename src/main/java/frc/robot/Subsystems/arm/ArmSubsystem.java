package frc.robot.Subsystems.arm;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class ArmSubsystem extends Subsystem {
    private static ArmSubsystem instance;
    private static final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static final TalonFX armMotor = new TalonFX(9);

    public static ArmSubsystem getInstance() {
        if (instance == null) {
            instance = new ArmSubsystem();
        }
        return instance;
    }

    public static ArmSubsystem subsystem() {
        return getInstance();
    }
    public ArmSubsystem(){
        armMotor.getConfigurator().apply(ArmConfig.config());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        pulling(1);

        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> idleIMethods(),
                    () -> emptyMethod(),
                    () -> idleEMethods()
            },
            {
                    () -> pullingIMethods(),
                    () -> emptyMethod(),
                    () -> pullingEMethods()
            },
    };

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    public void idleIMethods() {
        armMotor.setControl(Motion.withPosition(0));
    }

    public void idleEMethods() {
        armMotor.setControl(Motion.withPosition(0));
    }

    public void pullingIMethods() {
        armMotor.setControl(Motion.withPosition(0.25));
    }

    public void pullingEMethods() {
        armMotor.setControl(Motion.withPosition(0));
    }
}
