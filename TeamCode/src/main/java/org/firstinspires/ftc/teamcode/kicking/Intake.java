package org.firstinspires.ftc.teamcode.kicking;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    DcMotor intakeMotor;

    public Intake(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.dcMotor.get ("intakeMotor");

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setPower(0);
    }

    public void intake(Gamepad gamepad1) {
        if (gamepad1.right_bumper) {
            intakeMotor.setDirection (DcMotorSimple.Direction.FORWARD);
            intakeMotor.setPower(1);
        }
        else if (gamepad1.left_bumper) {
            intakeMotor.setDirection (DcMotorSimple.Direction.REVERSE);
            intakeMotor.setPower(1);
        }
        else {
            intakeMotor.setPower(0);
        }
    }
}
