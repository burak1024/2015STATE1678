package frc.robot.Subsystems.elevator;

public class ElevatorConstants {
    public static final Double[] SETPOINTS = { 0.0, 1.0, 3.0, 7.0, 10.0 };
    public static final int ELEVATOR_R_MOTOR_ID=9;
    public static final int ELEVATOR_L_MOTOR_ID = 10;
    public static final double kS = 1.0;
    public static final Double kV = 0.119;
    public static final Double kA = 0.01;
    public static final Double kP = 0.01;
    public static final Double kI = 0.01;
    public static final Double kD = 0.01;
    public static final Double CRUISE_VELOCITY = Double.POSITIVE_INFINITY;
    public static final Double ACCELERATION = Double.POSITIVE_INFINITY;
    public static final Double JERK = 0.0;
    public static final Double STATOR_CURRENT_LIMIT = 80.0;
}
