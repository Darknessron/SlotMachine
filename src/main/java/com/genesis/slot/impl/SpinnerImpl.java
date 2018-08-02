/**
 * 
 */
package com.genesis.slot.impl;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.genesis.exams.slot.Reel;
import com.genesis.exams.slot.Spinner;
import com.genesis.exams.slot.Symbol;

/**
 * @author Ron
 *
 */
@Component
public class SpinnerImpl implements Spinner {
    private final NavigableMap<Double, Symbol> map = new TreeMap<Double, Symbol>();
    private final Random random = new Random();
    private double total = 0;

	/* (non-Javadoc)
	 * @see com.genesis.exams.slot.Spinner#spin(com.genesis.exams.slot.Reel)
	 */
	@Override
	public Symbol spin(Reel reel) {
		for (Symbol symbol : reel.getSymbols())	{
			this.add(symbol);
		}
		double value = random.nextDouble() * total;
		Symbol result = map.higherEntry(value).getValue();
		cleanUp();
        return result;
	}


    private SpinnerImpl add(Symbol symbol) {
        if (symbol.getWeight() <= 0) return this;
        total += symbol.getWeight();
        map.put(total, symbol);
        return this;
    }
    
    private void cleanUp()	{
    	this.map.clear();
    	this.total = 0;
    }

}
