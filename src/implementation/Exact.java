package implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import interfaces.Job;
import interfaces.LinkedSchedule;
import interfaces.MinTarOPT;
import interfaces.Schedule;

public class Exact implements MinTarOPT{
	private List<Job> jobs;
	
	// Subproblems are denoted by params i,j,k 
	/*
	 * Algorithm:
	 *  Let the jobs be ordered in nondecreasing order
	 * 	Let n be number of jobs
	 * 	Let k be job with largest proc time
	 * 	Choose (each?) delta, such that 0 <= delta <= n - k
	 * 	then constructing OPTSEQ is: OPTSEQ(1....k-1, k+1, ... k+delta) followed by job k, followed by OPTSEQ(k+delta+1, .... n)
	 *  	!!!!	Minimize over all values of delta of course
	 *  Tard(empty interval, t) = 0
	 *  Tard({j}, t) = max(0, t+pj-dj)
	 *  
	 *  A problem instance is defined as a subset of the global set of jobs, constrained to both an interval and a max processing time p_k:
	 *  	S(i,j,k) = all jobs with i <= identifiers <= j, and processing time < p_k
	 *  	p_k is the processing time of the job with identifier k
	 */ 
	
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Exact";
		
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs; 
	}

	@Override
	public void processJobs() {
		// Sort jobs first? Oh well
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private class SubProblem {
		private List<Job> jobs;
		int n;
		int k;
		
		public SubProblem(int from, int to, int pk, List<Job> jobs){
			this.jobs = implementation.Util.split_problem(jobs, from, to, pk); 
			this.n = jobs.size();
			this.k = n-1; // assumes sorted input
		}
		
		public SubProblem(int from, int to, int pk){
			new SubProblem(from, to, pk, Exact.this.jobs);
		}
		
		public SubProblem split(int from, int to, int pk){
			return new SubProblem(from, to, pk, this.jobs);
		}
		
		public List<Integer> deltas() {
			List<Integer> retval = new ArrayList<Integer>();
			for(int i=0; i< (n-k); i++){
				retval.add(i);
			}
			return retval; // list all deltas 	
		}
		
		public Pair<SubProblem, SubProblem> split(int delta){
			// 0... k-1, k+1, ... k+delta for left <- incorrect
			int leftFrom = 0;
			
			return null;
		}
		
		
		// can reference Exact.this.jobs as well
	}

}

class Pair<L, R> {         
    public final L l;
    public final R r;

    public Pair(L l, R r) {         
        this.l= l;
        this.r= r;
     }
 }

class ExactSchedule implements LinkedSchedule{

	private LinkedSchedule prev;
	private Job job;
	
	public ExactSchedule(LinkedSchedule prev, Job job) {
		this.prev = prev;
		this.job = job;
	}

	@Override
	public int getTardiness() {
		int time = this.getTotalTime();
		
		int pt = prev != null?  prev.getTardiness() : 0;
		int ct = time > job.getDue() ? time - job.getDue() : 0;
		return pt + ct;
	}

	@Override
	public int getTotalTime() {
		int pt = prev != null? prev.getTotalTime() : 0;
		return pt + job.getDuration();
	}

	@Override
	public boolean contains(Job job) {		
		return job.equals(this.job) || (prev != null && prev.contains(job));
	}

	@Override
	public int compareTo(LinkedSchedule o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}

	

