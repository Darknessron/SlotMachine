package com.genesis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genesis.exams.slot.Evaluator;
import com.genesis.exams.slot.Reel;
import com.genesis.exams.slot.SlotMachine;
import com.genesis.exams.slot.SpinResult;
import com.genesis.exams.slot.Spinner;
import com.genesis.exams.slot.Symbol;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlotMachineApplicationTests {
	
	@Autowired
	private Spinner spinner;
	
	@Autowired
	private Evaluator evaluator;


	@Test
	public void testNoCheat()	{
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1), new Symbol("X", 2), new Symbol("Y", 3), new Symbol("Z", 4)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 4), new Symbol("Y", 3), new Symbol("X", 2), new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1), new Symbol("X", 2), new Symbol("Y", 3), new Symbol("Z", 4)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		Assert.assertEquals(0, result.getPayout());
	}

	@Test
	public void testAllA()	{
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 0), new Symbol("C", 0), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 0), new Symbol("Y", 0), new Symbol("X", 0), new Symbol("A", 1), new Symbol("B", 0), new Symbol("C", 0)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 0), new Symbol("C", 0), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		Assert.assertEquals(200, result.getPayout());
	}

	@Test
	public void testAllB()	{
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 0), new Symbol("B", 1), new Symbol("C", 0), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 0), new Symbol("Y", 0), new Symbol("X", 0), new Symbol("A", 0), new Symbol("B", 1), new Symbol("C", 0)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 0), new Symbol("B", 1), new Symbol("C", 0), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		Assert.assertEquals(200, result.getPayout());
	}

	@Test
	public void testAllC()	{
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 0), new Symbol("B", 0), new Symbol("C", 1), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 0), new Symbol("Y", 0), new Symbol("X", 0), new Symbol("A", 0), new Symbol("B", 0), new Symbol("C", 1)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 0), new Symbol("B", 0), new Symbol("C", 1), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		Assert.assertEquals(200, result.getPayout());
	}

	@Test
	public void testABC()	{
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 0), new Symbol("C", 0), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 0), new Symbol("Y", 0), new Symbol("X", 0), new Symbol("A", 0), new Symbol("B", 1), new Symbol("C", 0)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 0), new Symbol("B", 0), new Symbol("C", 1), new Symbol("X", 0), new Symbol("Y", 0), new Symbol("Z", 0)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		Assert.assertEquals(300, result.getPayout());
	}
}
