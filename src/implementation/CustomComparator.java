package implementation;

import java.util.Comparator;

import interfaces.Job;

class CustomComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
    	Integer i1, i2;
    	i1 = o1.getDue();
    	i2 = o2.getDue();
        return i1.compareTo(i2);
    }
}