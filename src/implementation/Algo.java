package implementation;
import java.util.ArrayList;
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
				op = jobs.get(j).getDuration();
			}
		}
		
		//Calculate max_delta
		int max_delta = n-k-1; // correction for 0 based indexes; // e.g. 5-4

		int tardiness = Integer.MAX_VALUE;
		//Calculate sub problems
		
		for(int delta = 0; delta <= max_delta; delta++){ //max delta will be 1:
			int sub_tardiness;
			List<Job> ji = new ArrayList<Job>(jobs.subList(0, k+delta+1));
			ji.remove(jobs.get(k));
			List<Job> jiii = new ArrayList<Job>(jobs.subList(k+delta+1, jobs.size())); // 4 + 1 + 1
			
			int ti = T(ji, t);
			int stk = Math.max(0,  
					t + C(ji)+jobs.get(k).getDuration()- // forgot to add t
					jobs.get(k).getDue());
			int tiii = T(jiii, t+C(ji)+jobs.get(k).getDuration()); // forgot to add t
			sub_tardiness = ti + stk + tiii;

			if(sub_tardiness < tardiness){
				tardiness = sub_tardiness;
			}
		}
		return tardiness;
	}
}