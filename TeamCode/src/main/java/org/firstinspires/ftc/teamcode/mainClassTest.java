package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class mainClassTest extends OpMode {

    DrivetrainTest drivetrainTest;
    TurretTest turretTest;
    IntakeTest intakeTest;
    TransferTest transferTest;
    FlywheelTest flywheelTest;

    @Override
    public void init() {
        drivetrainTest = new DrivetrainTest(hardwareMap);
        turretTest = new TurretTest(hardwareMap);
        intakeTest = new IntakeTest(hardwareMap);
        transferTest = new TransferTest(hardwareMap);
        flywheelTest = new FlywheelTest(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrainTest.drive(gamepad1);
        turretTest.turretManualControl(gamepad1);
        intakeTest.intakeManualControl(gamepad1);
        transferTest.transferManualControl(gamepad1);
        flywheelTest.flywheelManualControl(gamepad1);
    }
}
