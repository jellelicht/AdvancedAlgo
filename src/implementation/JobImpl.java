package implementation;

import interfaces.Job;

class JobImpl implements Job {
	private int duration;
	private int due;
	

	public JobImpl(int duration, int due) {
		this.duration = duration;
		this.due = due;
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}

	@Override
	public int getDue() {
		// TODO Auto-generated method stub
		return due;
	}
	
}