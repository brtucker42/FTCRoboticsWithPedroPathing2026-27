package org.firstinspires.ftc.teamcode.testing;

import static java.lang.Math.atan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TurretAutoTestSchurig {
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


    public double turretPosition = 0.0;

    private static final double turretIncrement = 30.0;

    public TurretAutoTestSchurig(HardwareMap hardwareMap) {
        turretMotor = hardwareMap.get(DcMotorEx.class,"turretMotor");
        turretMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Use in the future
        turretMotor.setVelocityPIDFCoefficients(p,i,d,f);
        //turretMotor.setVelocity(1, AngleUnit.DEGREES);
        turretMotor.setTargetPositionTolerance(3);
        turretMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        turretMotor.setTargetPosition(0);
        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turretMotor.setPower(0.5);

    }

    public void updateTelemetry(Telemetry telemetry) {
        int convertedMotor = turretMotor.getCurrentPosition();
        convertedMotor = (int) (convertedMotor/conversionFactor);

        telemetry.addData("Turret Position", convertedMotor);

        telemetry.addData("Turret Target", turretPosition);
    }

    public void moveToPosition(double position_deg) {
        if (turretPosition > 360) {
            turretPosition = turretPosition % 360;
        }
        if (turretPosition < 0) {
            turretPosition += 360;
        }
        turretPosition = position_deg;
        motorPosition = position_deg * conversionFactor;
        turretMotor.setTargetPosition((int) (motorPosition));

    }

    public void turretAutoAim(DrivetrainTestOdo drivetrainTestOdo) {
        double robotX = drivetrainTestOdo.follower.pose().x();
        double robotY = drivetrainTestOdo.follower.pose().y();
        double robotHeading = Math.toDegrees(drivetrainTestOdo.follower.pose().heading());

        double rad = Math.atan2(robotY, robotX);
        double deg = Math.toDegrees(rad);
        moveToPosition(deg - (360 - robotHeading));


        if (robotX > 0) {
            centerAngle = (Math.toDegrees(atan(robotY / robotX))) + 180;
        }
        else if (robotX < 0) {
            centerAngle = ((Math.toDegrees(atan(robotY/robotX))) + 360) % 360;
        }
        else {
            if (robotY > 0) {
                centerAngle = 270;
            }
            else {
                centerAngle = 90;
            }
        }

        moveToPosition(centerAngle - robotHeading);

    }
}
