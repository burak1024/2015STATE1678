package frc.robot.Subsystems.elevator;

import frc.robot.lib.Subsystem;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

public class ElevatorSubsystem extends Subsystem {
    private final TalonFX ElevatorMotorR = new TalonFX(ElevatorConstants.ELEVATOR_R_MOTOR_ID);
    private final TalonFX ElevatorMotorL = new TalonFX(ElevatorConstants.ELEVATOR_L_MOTOR_ID);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static ElevatorSubsystem instance;

    public static ElevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSubsystem();
        }
        return instance;
    }

    public static ElevatorSubsystem subsystem() {
        return getInstance();
    }
    public ElevatorSubsystem(){
        ElevatorMotorL.getConfigurator().apply(ElevatorConfig.config());
        ElevatorMotorR.getConfigurator().apply(ElevatorConfig.config());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        L1(1),
        L2(2),
        L3(3),
        L4(4);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> idleIMethods(),
                    () -> LayerPMethods(0),
                    () -> emptyMethod()
            },
            {
                    () -> LayerPMethods(1),
                    () -> LayerPMethods(1),
                    () -> emptyMethod()
            },
            {
                    () -> emptyMethod(),
                    () -> LayerPMethods(2),
                    () -> emptyMethod()
            },
            {
                    () -> emptyMethod(),
                    () -> LayerPMethods(3),
                    () -> emptyMethod()
            },
            {
                    () -> emptyMethod(),
                    () -> LayerPMethods(4),
                    () -> emptyMethod()
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
        ElevatorMotorL.setControl(Motion.withPosition(0));
        ElevatorMotorR.setControl(Motion.withPosition(0));
    }

    public void LayerPMethods(int setPos) {
        ElevatorMotorL.setControl(Motion.withPosition(ElevatorConstants.SETPOINTS[setPos]));
        ElevatorMotorR.setControl(Motion.withPosition(ElevatorConstants.SETPOINTS[setPos]));
    }

}