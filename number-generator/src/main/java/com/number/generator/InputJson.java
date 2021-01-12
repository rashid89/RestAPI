package com.number.generator;

public class InputJson {

	private Integer goal;
	private Integer step;
	
	public InputJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getGoal() {
		return goal;
	}
	public void setGoal(Integer goal) {
		this.goal = goal;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	@Override
	public String toString() {
		return "InputJson [goal=" + goal + ", steps=" + step + "]";
	}
	
}
