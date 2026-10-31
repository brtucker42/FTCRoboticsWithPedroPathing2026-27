package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TurretTest {
    DcMotorEx turretMotor;

    boolean leftPressed = false;
    boolean rightPressed = false;

    public int turretPosition = 0;

    private static final int turretIncrement = 20;

    public TurretTest(HardwareMap hardwareMap) {
        turretMotor = hardwareMap.get (DcMotorEx.class,"turretMotor");
        turretMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setVelocityPIDFCoefficients(10,0,0,12);
        turretMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        turretMotor.setVelocity(1, AngleUnit.DEGREES);
        turretMotor.setPower(0.1);
        turretMotor.setTargetPosition(0);

    }


    public void updateTelemetry(Telemetry telemetry) {

        telemetry.addData("Turret Position",
                turretMotor.getCurrentPosition());

        telemetry.addData("Turret Target",
                turretPosition);
    }

    public void turretManualControl(Gamepad gamepad1) {
        turretMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        if (gamepad1.dpad_left) {
            if (!leftPressed) {
                turretPosition -= 1;

            leftPressed = true;
            }
        }
        else {
            leftPressed = false;
        }

        if (gamepad1.dpad_right) {
            if (!rightPressed) {
                turretPosition += 1;
            }
            rightPressed = true;
        }
        else {
            rightPressed = false;
        }
        turretMotor.setTargetPosition(turretPosition);


    }





}
