package elevator;
import org.usfirst.frc.team9109.robot.*;
import driveTrain.*;
import edu.wpi.first.wpilibj.*;

public class Winch {
	public double targetDistance;
	public static XboxController myViceXbox = new XboxController(2);
	double sp;
	
	//distances between the bottom of the cube and the ground
	double distGround = 0;
	double distSwitch = 70;
	double distScale = 185;
	public Winch() {
		
	}
	PWMVictorSPX winchLeft = new PWMVictorSPX(4);
	PWMVictorSPX winchRight = new PWMVictorSPX(5);
	
	/** 
	 * @param sp the velocity of winch rotation; rolling up is positive
	 */
	public void rotate(double sp) { 
		winchRight.set(sp);
		winchLeft.set(-sp);
	}
	public void testControlledRoller() {
		sp = myViceXbox.getY(GenericHID.Hand.kLeft);
		rotate(-sp/1.5);
	}
		
	
	/**
	 * @param the velocity of winch rotation; rolling up is positive
	 * @param the distance between the intake and the ground
	 */
	/*public void goStraight(double sp, double distance) {
		if(this.targetDistance != distance)
			this.targetDistance = distance;
		if (getWinchDistance() < distance) {
				rotate(sp);
		}
	}
	
	public void stageGround() {
		goStraight(0.3,distGround - targetDistance);
	}
	
	public void stageSwitch() {
		goStraight(0.2,distSwitch - targetDistance);
	}
	
	public void stageScale() {
		goStraight(0.3,distScale - targetDistance);
	}*/
}
