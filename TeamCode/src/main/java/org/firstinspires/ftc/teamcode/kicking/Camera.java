package org.firstinspires.ftc.teamcode.kicking;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

// This class still needs to be developed .

public class Camera {

    private  Limelight3A limelight;

    public Camera(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
    }

    public void init() {
        limelight.start();
    }

    public boolean seeBarcode(boolean posSide) {
        if(posSide){
            //look for pos barcode
        }
        else{
            //look for neg barcode
        }
        return true;
    }

    public double getXOffset(boolean posSide) {
        LLResult result = limelight.getLatestResult();
        double Tx = 0.0;
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose();
                Tx = result.getTx();
            }
        }
        return Tx;
    }

    public double getYOffset(boolean posSide) {
        LLResult result = limelight.getLatestResult();
        double Ty = 0.0;
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose();
                Ty = result.getTy();
            }
        }
        return Ty;
    }
}
