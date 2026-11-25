package org.firstinspires.ftc.teamcode.kicking;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter {
    DcMotorEx flywheelMotor;
    boolean flywheelOn = false;
    public double TargetVelocity = 1400;

    public double range = 25;
    public double flywheelVelocity;

    public Shooter(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class,"flywheelMotor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setVelocityPIDFCoefficients(10,0,0,12);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelMotor.setPower(0);

    }

    public void manualFlywheel(Gamepad gamepad1) {

        // Set variables
        if (gamepad1.aWasPressed()) {
            flywheelOn = true;
        }


        if (gamepad1.bWasPressed()) {
            TargetVelocity += 100;
        }

        if (gamepad1.x) {
            TargetVelocity = Math.max(0, TargetVelocity -100);
        }


        // Set motor
        if (flywheelOn){
            flywheelMotor.setVelocity(TargetVelocity);
            // flywheelMotor.setPower(0.5);
        }
        else {
            flywheelMotor.setPower(0);
        }

        flywheelVelocity = flywheelMotor.getVelocity();

    }

    public void autoFlywheel() {

    }

    public void manualHood(Gamepad gamepad1) {

    }

    public void autoHood() {

    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Flywheel velocity: ", flywheelVelocity);
        telemetry.addData("Flywheel target velocity: ", TargetVelocity);
        telemetry.addData("Range: ", range);
    }
}
