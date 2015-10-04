package implementation;
import java.util.List;

import interfaces.Job;

public class Algo {
	public int C(List<Job> jobs){
		int sum = 0;
		
		for(Job j: jobs){
			sum += j.getDuration();
		}
		
		return sum;
	}
	
	public int T(List<Job> jobs, int t){
		int n = jobs.size();
		
		//Handle base cases
		if (n == 0){
			return 0;
		} else if (n == 1){
			Job j = jobs.get(0);
			return Math.max(t+j.getDuration()-j.getDue(), 0);
		}
		
		int op = jobs.get(0).getDuration();
		int k = 0;
		
		//Calculate k
		for(int j = 1; j < n; j++){
			if(jobs.get(j).getDuration() > op){
				k =j;
			}
		}
		
		//Calculate max_delta
		int max_delta = n-k;

		int tardiness = Integer.MAX_VALUE;
		//Calculate sub problems
		
		for(int delta = 0; delta < max_delta+1; delta++){
			int sub_tardiness = 0;
			
			List<Job> ji = jobs.subList(0, k+delta+1);
			ji.remove(k);
			List<Job> jiii = jobs.subList(k+delta+1, jobs.size());
			
			sub_tardiness += T(ji, t);
			sub_tardiness += Math.max(0,  C(ji)+jobs.get(k).getDuration()-jobs.get(k).getDue());
			sub_tardiness += T(jiii, C(ji)+jobs.get(k).getDuration());
			
			if(sub_tardiness < tardiness){
				tardiness = sub_tardiness;
			}
		}
		return tardiness;
	}
}