package driveTrain;

import org.usfirst.frc.team9109.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import elevator.Winch;
import intake.Intake;

public class Chassis {
	
	public static XboxController myXbox = new XboxController(1);
	
	public int autoRoutineNum = 0;

	private double minv = 0.4;
	private double maxv = 0.6;
	private double drift = 0.05;
	private double sp, steering, mod, r;

	public static PWMVictorSPX m_leftFront = new PWMVictorSPX(2);
	public static PWMVictorSPX m_leftBack = new PWMVictorSPX(3);
	public static PWMVictorSPX m_rightFront = new PWMVictorSPX(0);
	public static PWMVictorSPX m_rightBack = new PWMVictorSPX(1);

	public static SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftBack);
	public static SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightBack);
	public static DifferentialDrive myRobot = new DifferentialDrive(m_left, m_right);
	
	/*public AutoDrive myAuto = new AutoDrive();
	public Winch autoWinch = new Winch();
	public Intake autoIntake = new Intake();
	boolean autoIsFinished = false;*/
	
	public static String gameData = "L";
	
	public static Timer myTimer1 = new Timer();
	
	public Chassis() {
	}

	/*public boolean isDrifting() {
		boolean flag = false;
		if (this.sp < drift)
			flag = true;
		return flag;
	}*/

	public void regularDrive() {
		sp = myXbox.getY(GenericHID.Hand.kLeft);
		steering = (myXbox.getX(GenericHID.Hand.kRight) + 0.1 * sp) / 1.3;
		//if (!isDrifting())
			myRobot.arcadeDrive(sp * 0.7, steering);
	}

	public void moderateDrive() {
		sp = myXbox.getY(GenericHID.Hand.kLeft);
		steering = myXbox.getX(GenericHID.Hand.kRight);
		//if (!isDrifting()) {
			mod = minv + maxv * (Math.pow(1 - Math.abs(sp), 2));
			r = Math.pow(steering, 3) * mod / 1.2;
			myRobot.arcadeDrive(sp * 0.7, r);
		//}
	}

	public void simpleDrive() {
		sp = myXbox.getY(GenericHID.Hand.kLeft);
		steering = myXbox.getX(GenericHID.Hand.kRight);
		//if (!isDrifting())
			myRobot.arcadeDrive(sp * 0.8, steering);
	}

	/*public void setAutoRoutine(int caseNum) {
		autoRoutineNum = caseNum;
	}

	/*public void autoRoutine0() {

		myAuto.goStraight(.5, 200);

	}

	public void autoRoutine1() {
		myAuto.goStraight(.5, 300);
	}*/

	/*public void runAuto() {

		switch (autoRoutineNum) {
		case 0:
			defaultRoutine();
			break;
		case 1:
			leftRoutine(gameData);
			break;
		case 2:
			middleRoutine(gameData);
			break;
		case 3:
			rightRoutine(gameData);
			break;
		}
	}
	public void leftRoutine(String data) {
		if(gameData.substring(0, 1) == "L") {
			
			if(myTimer1.get()<0.8) {
				myAuto.goStraight(0.6);
				autoWinch.rotate(0.2);
			}
			myTimer1.reset();
			if(myTimer1.get()<4)
				myAuto.turn(0.7, 90);
			myTimer1.reset();
			if(myTimer1.get()<0.3)
				myAuto.goStraight(0.3);
			myTimer1.reset();
			if(myTimer1.get()<3)
				autoIntake.unGrab();
		}else {
			if(myTimer1.get()<0.8)
				myAuto.goStraight(0.6);
		}
	}
	
	public void middleRoutine(String data) {
		if(gameData.substring(0, 1) == "L") {
			
		}else {
			
		}
	}
	
	public void rightRoutine(String data) {
		if(gameData.substring(0, 1) == "L") {
			if(myTimer1.get()<0.8)
				myAuto.goStraight(0.6);
		}else {
			
		}
	}
	
	public void defaultRoutine() {
		if(myTimer1.get()<0.8)
			myAuto.goStraight(0.6);
	}
	/*public void autoRoutine(int caseNum) {
		switch(caseNum) {
		case 1:
			
		}
	}*/
}
