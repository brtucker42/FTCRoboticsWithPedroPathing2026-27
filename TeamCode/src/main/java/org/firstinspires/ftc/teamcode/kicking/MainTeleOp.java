package org.firstinspires.ftc.teamcode.kicking;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends OpMode {

    Drivetrain drivetrain;
    Intake intake;
    Transfer transfer;
    Shooter shooter;
    Turret turret;
    Camera camera;


    int blueSco = 0;
    int blueAud = 1;
    int redSco = 2;
    int redAud = 3;


    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        transfer = new Transfer(hardwareMap);
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap);
        camera = new Camera(hardwareMap);
        camera.init();
    }

    @Override
    public void loop() {
        drivetrain.manualDrive(gamepad1);
        intake.intake(gamepad1);
        transfer.manualTransfer(gamepad1);
        shooter.manualFlywheel(gamepad1);
        shooter.manualHood(gamepad1);
        turret.turretAutoAim(drivetrain, telemetry);

        telemetry.addData("tx", camera.getXOffset());
        telemetry.addData("ty", camera.getYOffset());

        turret.updateTelemetry(drivetrain, telemetry);
        drivetrain.updateTelemetry(telemetry);
        shooter.updateTelemetry(telemetry);
        telemetry.update();
    }
}