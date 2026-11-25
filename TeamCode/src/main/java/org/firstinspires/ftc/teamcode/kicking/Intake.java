package org.firstinspires.ftc.teamcode.kicking;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    DcMotor intakeMotor;

    double power = 0.5;

    public Intake(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.dcMotor.get ("intakeMotor");

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setPower(0);
    }

    public void intake(Gamepad gamepad1) {
        if (gamepad1.rightBumperWasPressed()) {
            intakeMotor.setDirection (DcMotorSimple.Direction.FORWARD);
            intakeMotor.setPower(power);
        }
        else if (gamepad1.leftBumperWasPressed()) {
            intakeMotor.setDirection (DcMotorSimple.Direction.REVERSE);
            intakeMotor.setPower(power);
        }
        else {
            intakeMotor.setPower(0);
        }
    }
}
