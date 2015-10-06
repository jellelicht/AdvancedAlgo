package implementation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import external.algorithms;
import interfaces.Job;
import interfaces.MinTar;

public class Util {
	public static List<List<Job>> permutations(List<Job> jobs){
		List<List<Job>> result = new ArrayList<List<Job>>();
		result.add(new ArrayList<Job>());
		
		for(int i = 0; i < jobs.size(); i++){
			List<List<Job>> current = new ArrayList<List<Job>>();
			for(List<Job> l : result) {
				for(int j = 0; j< l.size()+1; j++){
					l.add(j, jobs.get(i));
					List<Job> temp = new ArrayList<Job>(l);
					current.add(temp);
					l.remove(j);
				}
			}
			
			result = new ArrayList<List<Job>>(current);
		}
		return result;
	}
	public static String report(List<Job> jobs, int t){
		String x = "[";
		for(Job z : jobs){
			x += "J{" + z.getDuration() + ", " + z.getDue() + "}, ";
		}
		x += "] =>" + computeTardiness(jobs, t);
		return x;
	}
	public static List<MinTar> strategies(){
		List<MinTar> retval = new ArrayList<MinTar>();
		// Add more algorithms here:
		//MinTar noop = new Noop();
		//retval.add(noop);
		//MinTar greedy = algorithms.greedy();
		//retval.add(greedy);
		//MinTar bestfirst = algorithms.bestfirst();
		//retval.add(bestfirst);	
		//MinTar brute = new BruteForce();
		//retval.add(brute);
		MinTar algowrapper = new AlgoWrapper();
		retval.add(algowrapper);
		//
		
		return retval;
	}
	public static List<Job> split_problem(List<Job> jobs, int from, int to, int pk){
		List<Job> retval = new ArrayList<Job>();
		for(Job j: jobs.subList(from, to+1)){
			if(j.getDuration() < pk){
				retval.add(j);
			}
		}
		return retval;
	}
	
	public static int computeTardiness(List<Job> jobs, int t){
		int tardiness = 0;
		int total = t;
		for(Job j : jobs){
			total+= j.getDuration();
			if(j.getDue() < total){
				tardiness += total - j.getDue();
			}
		}
		return tardiness;	
	}
	public static int computeTMax(List<Job> jobs, int t){
		
		int maxTardiness = Integer.MIN_VALUE;
		int total = t;
		for(Job j : jobs){
			total+= j.getDuration();
			if(j.getDue() < total){
				int tardiness = total - j.getDue();
				if(tardiness > maxTardiness){
					maxTardiness = tardiness;
				}
				
			}
		}
		return maxTardiness;	
	}
	
	public static int C(List<Job> jobs){
		
		int sum = 0;
		
		for(Job j: jobs){
			sum += j.getDuration();
		}
		
		return sum;
	}
	
	public static List<Job> read_problem(String text_file) {
		Scanner s = null;
		List<Job> jobs = null;// = new ArrayList<Job>();
		int NumJobs;
		try {
			s = new Scanner(new BufferedReader(new FileReader(text_file)));
			if (s.hasNextInt()) {
				NumJobs = s.nextInt();
				jobs = new ArrayList<Job>(NumJobs);
				int job = 0;

				while (s.hasNextInt() && job < NumJobs) {
					int l = s.nextInt();
					int d = s.nextInt(); // unsafe?
					jobs.add(new JobImpl(l, d));
					++job;
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(jobs != null){ 
			return jobs;
		}
		return new ArrayList<Job>(); // just supply empty list if nothing is available
	}
}
