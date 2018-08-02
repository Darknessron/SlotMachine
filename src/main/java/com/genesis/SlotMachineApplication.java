package com.genesis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.genesis.exams.slot.Evaluator;
import com.genesis.exams.slot.Reel;
import com.genesis.exams.slot.SlotMachine;
import com.genesis.exams.slot.SpinResult;
import com.genesis.exams.slot.Spinner;
import com.genesis.exams.slot.Symbol;

@SpringBootApplication
public class SlotMachineApplication {

	private static final Logger logger = LogManager.getLogger(SlotMachineApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SlotMachineApplication.class, args);
		
		Spinner spinner = context.getBean(Spinner.class);
		Evaluator evaluator = context.getBean(Evaluator.class);
		
		Reel[] reels = new Reel[3];
		
		reels[0] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1), new Symbol("X", 2), new Symbol("Y", 3), new Symbol("Z", 4)});
		reels[1] = new Reel(new Symbol[] {new Symbol("Z", 4), new Symbol("Y", 3), new Symbol("X", 2), new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1)});
		reels[2] = new Reel(new Symbol[] {new Symbol("A", 1), new Symbol("B", 1), new Symbol("C", 1), new Symbol("X", 2), new Symbol("Y", 3), new Symbol("Z", 4)});
		
		SlotMachine machine = new SlotMachine(reels, spinner, evaluator);
		
		SpinResult result = machine.spin(10);
		StringBuilder sb = new StringBuilder();
		for (Symbol symbol : result.getSymbols()) {
			sb.append(symbol.getName());
		}
		
		logger.info("Symbols : {}, Payout : {}", sb.toString(), result.getPayout());
	}
}
