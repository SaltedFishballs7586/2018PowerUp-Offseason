
package intake;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import org.usfirst.frc.team9109.robot.Robot;

import driveTrain.Chassis.*;
import elevator.Winch;

public class Intake {
	double sp;	
	
	static PWMVictorSPX m_right = new PWMVictorSPX(6);
	static PWMVictorSPX m_left = new PWMVictorSPX(7);
	public static DoubleSolenoid solenoid = new DoubleSolenoid(0,1);
	
	public static void rotate(double sp) {
		m_left.set(sp);
		m_right.set(-sp);
	}
	public void testControlledRoller() {
		sp = Winch.myViceXbox.getY(GenericHID.Hand.kRight);
		rotate(sp);
	}
	
	public static double intakeSpeed = 0.5;
	public static double outtakeSpeed = 0.4;
	
	public boolean touched = false;
	
	public Intake() {
	}
	
	public static void suck() {
		rotate(intakeSpeed);
		
	}
	
	public static void vomit() {
		rotate(-outtakeSpeed);
	}
	
	public static void stop() {
		rotate(0);
	}
	

	
	/*public void intakeCube() {
		if(!touched)
			suck();
	}
	
	public void outtakeCube() {
		vomit();
	}*/
	
	public static void grab() {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void unGrab() {
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public static void neutral() {
		solenoid.set(DoubleSolenoid.Value.kOff);
	}
}
