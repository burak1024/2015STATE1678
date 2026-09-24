package frc.robot.Subsystems.intake;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private static final TalonFX IntakeGrapperR = new TalonFX(IntakeConstants.INTAKE_GRIPPER_R_ID);
    private static final TalonFX IntakeGrapperL = new TalonFX(IntakeConstants.INTAKE_GRIPPER_L_ID);
    private static final TalonFX IntakeRollerR = new TalonFX(IntakeConstants.INTAKE_ROLLER_R_ID);
    private static final TalonFX IntakeRollerL = new TalonFX(IntakeConstants.INTAKE_ROLLER_L_ID);
    private static MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static IntakeSubsystem instance;

    public static IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public static IntakeSubsystem subsystem() {
        return getInstance();
    }
    public IntakeSubsystem(){
        IntakeGrapperL.getConfigurator().apply(IntakeConfig.Rollerconfig());
        IntakeRollerR.getConfigurator().apply(IntakeConfig.Rollerconfig());
        IntakeGrapperR.getConfigurator().apply(IntakeConfig.reverseConfig());
        IntakeGrapperL.getConfigurator().apply(IntakeConfig.reverseConfig());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1),
        grapping(2);

        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> idleIMethods(),
                    () -> emptyMethod(),
                    () -> emptyMethod()
            },

            {
                    () -> intakingIMethods(),
                    () -> intakingPMethods(),
                    () -> intakingEMethods()
            },
            {
                    () -> grapIMethods(),
                    () -> grapPMethods(),
                    () -> grapEMethods()
            }
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
        IntakeGrapperL.setControl(Motion.withPosition(0));
        IntakeGrapperR.setControl(Motion.withPosition(0));
        IntakeRollerL.setControl(Voltage.withOutput(0));
        IntakeRollerR.setControl(Voltage.withOutput(0));
    }

    public void intakingIMethods() {
        IntakeGrapperL.setControl(Motion.withPosition(0.05));
        IntakeGrapperR.setControl(Motion.withPosition(0.05));
        IntakeRollerL.setControl(Voltage.withOutput(3.0));
        IntakeRollerR.setControl(Voltage.withOutput(3.0));
    }

    public void intakingPMethods() {
        IntakeRollerL.setControl(Voltage.withOutput(3.0));
        IntakeRollerR.setControl(Voltage.withOutput(3.0));
    }

    public void intakingEMethods() {
        IntakeGrapperL.setControl(Motion.withPosition(0));
        IntakeGrapperR.setControl(Motion.withPosition(0));
        IntakeRollerL.setControl(Voltage.withOutput(0));
        IntakeRollerR.setControl(Voltage.withOutput(0));
    }

    public void grapIMethods() {
        IntakeGrapperL.setControl(Motion.withPosition(0));
        IntakeGrapperR.setControl(Motion.withPosition(0));
        IntakeRollerL.setControl(Voltage.withOutput(0));
        IntakeRollerR.setControl(Voltage.withOutput(0));
    }

    public void grapPMethods() {
        IntakeGrapperL.setControl(Motion.withPosition(0.05));
        IntakeGrapperR.setControl(Motion.withPosition(0.05));
    }

    public void grapEMethods() {
        IntakeGrapperL.setControl(Motion.withPosition(0));
        IntakeGrapperR.setControl(Motion.withPosition(0));
    }

}
