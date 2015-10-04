package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import interfaces.Job;
import interfaces.MinTarOPT;

class CustomComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
    	Integer i1, i2;
    	i1 = o1.getDue();
    	i2 = o2.getDue();
        return i1.compareTo(i2);
    }
}

public class AlgoWrapper implements MinTarOPT {
	private Algo algo = new Algo();
	private List<Job> jobs;
	private int t = Integer.MAX_VALUE;
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "AlgoWrapper";
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = new ArrayList<Job>(jobs.size());
		for(Job j: jobs){
			this.jobs.add(j);
		}
		Collections.sort(this.jobs, new CustomComparator());
		
	}

	@Override
	public void processJobs() {
		// TODO Auto-generated method stub
		this.t = algo.T(jobs, 0);
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return t;
	}

}
