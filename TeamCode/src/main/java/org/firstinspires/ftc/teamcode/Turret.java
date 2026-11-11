package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Turret {
    DcMotorEx turretMotor;

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;
    double ticksPerRev = 3.468;

    // 99 = teeth in big gear. 22 = teeth in little gear.
    double conversionFactor = (99.0/22.0)/ticksPerRev;

    public double motorPosition = 0;

    double p = 11;
    double i = 1;
    double d = 0.015;
    double f = 5;


    public double turretPosition = 0.0;

    private static final double turretIncrement = 30.0;

    public Turret(HardwareMap hardwareMap) {
        turretMotor = hardwareMap.get (DcMotorEx.class,"turretMotor");
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

        telemetry.addData("Current P ", p);
        telemetry.addData("Current I ", i);
        telemetry.addData("Current D ", d);
        telemetry.addData("Current F ", f);
    }

    public void turretManualControl(Gamepad gamepad1) {
        if (gamepad1.dpad_left) {
            if (!leftPressed) {
                turretPosition -= turretIncrement;
            }
            leftPressed = true;

        }
        else {
            leftPressed = false;
        }

        if (gamepad1.dpad_right) {
            if (!rightPressed) {
                turretPosition += turretIncrement;
            }
            rightPressed = true;
        }
        else {
            rightPressed = false;
        }
        motorPosition = turretPosition * conversionFactor;
        turretMotor.setTargetPosition((int) (motorPosition));


    }


    public void moveToPosition(int position_deg) {
        turretPosition = position_deg;
        motorPosition = position_deg * conversionFactor;
        turretMotor.setTargetPosition((int) (motorPosition));

    }

    public void tuneP(Gamepad gamepad1) {
        if (gamepad1.dpad_up) {
            if (!upPressed) {
                f += 1;
                turretMotor.setVelocityPIDFCoefficients(p,i,d, f);
            }
            upPressed = true;

        }
        else {
            upPressed = false;
        }
        if (gamepad1.dpad_down) {
            if (!downPressed) {
                f -= 1;
                turretMotor.setVelocityPIDFCoefficients(p,i,d, f);
            }
            downPressed = true;

        }
        else {
            downPressed = false;
        }



    }
}
