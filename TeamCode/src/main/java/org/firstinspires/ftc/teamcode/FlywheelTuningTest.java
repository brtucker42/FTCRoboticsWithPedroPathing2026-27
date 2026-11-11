package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class FlywheelTuningTest extends OpMode {

    public DcMotorEx flywheelMotor;

    public

   public double highVelocity = 1500;
   public double lowVelocity = 900;

   double curTargetVelocity = highVelocity;

   double F = 0;
   double P = 0;

   double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};

   int stepIndex = 0;

    public  void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("Init complete");

    }

    public  void loop() {
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
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        // set velovciyty
        flywheelMotor.setVelocity(curTargetVelocity);

        double curVelocity = flywheelMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("Targetvelocity", curTargetVelocity);
        telemetry.addData("Current velocity", curVelocity);
        telemetry.addData("Error", "%2f", error);
        telemetry.addLine("_________________________");
        telemetry.addData("Tuning p", "%2f (D-Pad U/D)", P);
        telemetry.addData("Tuning F", "%2f (D-Pad L/R)", F);
        telemetry.addData("Step size","%2f (B Button)", stepSizes[stepIndex]);
    }
}
