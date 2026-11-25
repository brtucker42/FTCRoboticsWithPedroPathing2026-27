package org.firstinspires.ftc.teamcode.kicking;

import static java.lang.Math.atan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Turret {
    DcMotorEx turretMotor;
    double ticksPerRev = 3.468;

    // 99 = teeth in big gear. 22 = teeth in little gear.
    double conversionFactor = (99.0/22.0)/ticksPerRev;

    public double motorPosition = 0;

    double p = 11;
    double i = 1;
    double d = 0.015;
    double f = 5;

    double centerAngle = 0;

    int turretTolerance = 5;

    public double turretPosition = 0.0;

    private static final double turretIncrement = 30.0;

    public Turret (HardwareMap hardwareMap) {
        turretMotor = hardwareMap.get(DcMotorEx.class,"turretMotor");
        turretMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Use in the future
        turretMotor.setVelocityPIDFCoefficients(p,i,d,f);
        //turretMotor.setVelocity(1, AngleUnit.DEGREES);
        turretMotor.setTargetPositionTolerance(turretTolerance);
        turretMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        turretMotor.setTargetPosition(0);
        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turretMotor.setPower(0.5);

    }

    public void moveToPosition(double position_deg) {
        if (!turretMotor.isBusy()) {
            turretPosition = position_deg;
            if (turretPosition > 360) {
                turretPosition = turretPosition % 360;
            }
            if (turretPosition < 0) {
                turretPosition += 360;
            }
            motorPosition = turretPosition * conversionFactor;
            turretMotor.setTargetPosition((int) (motorPosition));
        }

    }

    public void updateTelemetry(Drivetrain drivetrain, Telemetry telemetry) {
        double deg = Math.toDegrees(Math.atan2(drivetrain.follower.pose().y(), drivetrain.follower.pose().x()));

        telemetry.addData("Angle determined from field x,y pos", deg);
        telemetry.addData("Current turret position (angle)", turretPosition);
        telemetry.addData("Turret target position (angle)", (((deg + 180) % 360) - Math.toDegrees(drivetrain.follower.pose().heading())));
    }

    public void turretAutoAim(Drivetrain drivetrain, Telemetry telemetry) {
        double robotX = drivetrain.follower.pose().x();
        double robotY = drivetrain.follower.pose().y();
        double robotHeading = Math.toDegrees(drivetrain.follower.pose().heading());

        double rad = Math.atan2(robotY, robotX);
        double deg = Math.toDegrees(rad);
        if (deg < 0) {
            deg += 360;
        }

        moveToPosition((((deg + 180) % 360) - robotHeading));
    }
}