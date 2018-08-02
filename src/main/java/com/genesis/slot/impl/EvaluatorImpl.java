/**
 * 
 */
package com.genesis.slot.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.genesis.exams.slot.Evaluator;
import com.genesis.exams.slot.Symbol;

/**
 * @author Ron
 *
 */
@Component
public class EvaluatorImpl implements Evaluator { 

	private static final Logger logger = LogManager.getLogger(Evaluator.class);
	
	private static final String ALL_A = "AAA";
	private static final String ALL_B = "BBB";
	private static final String ALL_C = "CCC";
	private static final String ABC = "ABC";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genesis.exams.slot.Evaluator#evaluate(com.genesis.exams.slot.Symbol[],
	 * long)
	 */
	@Override
	public long evaluate(Symbol[] symbols, long bet) {
		int multiplier = 0;
		StringBuilder sb = new StringBuilder();
		for (Symbol symbol : symbols) {
			sb.append(symbol.getName());
		}
		String symbol = sb.toString(); 
		switch (symbol) {
			case ALL_A:
			case ALL_B:
			case ALL_C:
				multiplier = 20;
				break;
			case ABC:
				multiplier = 30;
				break;
		}
		long payout = multiplier * bet;
		logger.info("Symbols : {}, Payout : {}", symbol, payout);
		return payout;
	}

}
