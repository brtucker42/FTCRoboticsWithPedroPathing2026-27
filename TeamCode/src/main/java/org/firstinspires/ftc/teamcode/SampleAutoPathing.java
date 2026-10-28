/*package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.utils.Timer;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class SampleAutoPathing extends OpMode {


    private Follower follower;
    private Timer pathTimer, opModeTimer;

    public enum PathState {
        // START POSITION - END POSITION
        // DRIVE = MOVEMENT STATE
        // SHOOT = ATTEMPT TO SCORE ARTIFACT
        DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD
    }

    PathState pathState;

    private final Pose startPose = new Pose(20, 20, Math.toRadians(90));

    private final Pose shootPose = new Pose(20, 70, Math.toRadians(0));

    private PathChain driveStartPosShootPos;

    public void buildPaths() {
        // put in cords for starting pose - ending pose
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine (startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build;

    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                // Brogans vid had follower.followPath but that came to an error
                follower.follow(driveStartPosShootPos, true);
                // Reset timer and make new state
                setPathState(PathState.SHOOT_PRELOAD);
                break;
            case SHOOT_PRELOAD:
                // Check if follower is done with its path
                if (!follower.isBusy()) {
                    // Add flywheel logic
                    telemetry.addLine("Done Path 1");
                }
                break;
            default:
                telemetry.addLine("No State Commanded");
                break;
        }

    }

    public void setPathState(PathState newState) {
        pathState = newState;
        // Vid had resetTimer
        pathTimer.reset();

    }

    @Override
    public void init() {
        pathState  = PathState.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.create(hardwareMap);
        // Add in any other inits as needed

        buildPaths();
        follower.setPose(startPose);


    }

    public void start() {}

    @Override
    public void loop() {



    }




}
*/