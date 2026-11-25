package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlywheelTuningTest{

    public DcMotorEx flywheelMotor;

    PIDFCoefficients pidfCoefficients;
    public double highVelocity = 1500;
   public double lowVelocity = 900;

   double curTargetVelocity = highVelocity;

   double F = 13.6;
   double P = 53;

   double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};

   int stepIndex = 0;

    public  void init(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
       // telemetry.addLine("Init complete");

    }

    public  void flywheelManualControl(Gamepad gamepad1, Telemetry telemetry) {
        //get all the gamepad commandses
        // set yarget velocity
        // update tyelemetry



        if (gamepad1.yWasPressed()) {
            if (curTargetVelocity == highVelocity) {
                curTargetVelocity = lowVelocity;
            } else { curTargetVelocity = highVelocity;}
        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes[stepIndex];
        }

        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()) {
            P += stepSizes[stepIndex];
        }

        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes[stepIndex];
        }

        //set new pdf coefficients;
        pidfCoefficients.p = P;
        pidfCoefficients.f = F;
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // set velovciyty
        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = flywheelMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("Target velocity", curTargetVelocity);
        telemetry.addData("Current velocity", curVelocity);
        telemetry.addData("Error", "%2f", error);
        telemetry.addLine("_________________________");
        telemetry.addData("Tuning p", "%2f (D-Pad U/D)", P);
        telemetry.addData("Tuning F", "%2f (D-Pad L/R)", F);
        telemetry.addData("Step size","%2f (B Button)", stepSizes[stepIndex]);
    }
}
