package com.capgemini.bowling;

public class StandardFrame implements Frame {

	private Integer firstRoll = 0;
	private Integer secondRoll = 0;
	private Integer attemps = 0;
	public Frame nextFrame = null;

	public StandardFrame() {
	}

	public void addScore(Integer numberOfPins) throws IllegalArgumentException {
		if (attemps.equals(Integer.valueOf(0))) {
			firstRoll = numberOfPins;
		}
		if (attemps.equals(Integer.valueOf(1))) {
			secondRoll = numberOfPins;
		}
		if (firstRoll + secondRoll > Integer.valueOf(10)) {
			throw new IllegalArgumentException("sum of two rolls in frame over 10 ");
		}
		
		attemps++;		
	}

	public Boolean isDone() {
		return (attemps.equals(Integer.valueOf(2)) || attemps.equals(Integer.valueOf(1)) && isStrike());
	}

	public Integer getScore() {
		return firstRoll + secondRoll + getBonus();
	}

	private Integer getBonus() {
		Integer bonusPoint = 0;
		if (nextFrame != null) {
			if (isSpare()) {
				bonusPoint = nextFrame.getNextRoll();
			}
			if (isStrike()) {
				bonusPoint = nextFrame.getNextTwoRolls();
			}
		}
		return bonusPoint;
	}

	public Boolean isSpare() {
		return (!isStrike() && Integer.valueOf(10).equals(firstRoll+secondRoll));
	}

	public Integer getNextRoll() {
		return firstRoll;
	}

	public Boolean isStrike() {
		return (firstRoll.equals(Integer.valueOf(10)));
	}
	
	public Integer getNextTwoRolls() {
		if (!isStrike()) {
			return firstRoll + secondRoll;
		}
		if (isStrike() && nextFrame != null) {
			return firstRoll + nextFrame.getNextRoll();
		}
		return firstRoll;
	}
	
	public void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
}