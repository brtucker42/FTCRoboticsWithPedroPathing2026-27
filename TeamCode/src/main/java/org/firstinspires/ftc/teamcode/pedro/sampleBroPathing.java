package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class sampleBroPathing extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    public  enum  PathState {
        // START POSITION_END POSITION
        //DRIVE > MOVEMENT STATE
        // SHOOT > ATTEMPT TO SCORE

        DRIVE_STARTPOS_SHOOT_POS,

        SHOOT_PRELOAD
    }

    PathState pathState;

    private final Pose startPose = new Pose(56, 8, Math.toRadians(90));
    private final Pose shootPose = new Pose(56, 36, Math.toRadians(90));

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
