package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class TeleOpOdometry extends OpMode {
    private Follower follower;

    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(OpModeStorage.autonomousEndPose);
        follower.update();
    }

    public void loop(){
        double forward = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        //field-centric
        //DrivePowers powers = ManualDrive.fieldCentric(forward, lateral, turn, follower.pose().heading());
        //follower.manual(powers);
        //ManualDrive.driveOrHold(follower, powers);

        ManualDrive.driveOrHold(follower, forward, lateral, turn);

        //

        follower.update();
        Pose robotPose = follower.pose();
        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
        telemetry.update();
    }
}
