package com.primegenerator.service;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class PrimeGenerator {
	
	/**
	 * Method to generate Prime Numbers upto number n based on Sieve of Eratosthenes algorithm
	 * @param n
	 * @return
	 */
	public ArrayList<Integer> generatePrime(Integer n) throws Exception{
		
		ArrayList<Integer> primeNumberList = new ArrayList<Integer>();
		
		int[] primeArray = new int[n+1];
		
		for(int i=0; i< n+1; i++){
			primeArray[i]= 1;
		}
		
		int upperLimit = (int) Math.sqrt(n);
		System.out.println("Upper Limit is: "+upperLimit);
		System.out.println("******Prime Numbers are as Follows*****");
		
		primeArray[0]=0;
		primeArray[1]=0;
		
		for(int i=2; i<=upperLimit; i++){
			if(primeArray[i] == 1){
				for(int j=2; i*j <=n ; j++){
					primeArray[i*j] = 0;
				}
			}
		}
		
		for(int k=0;k<primeArray.length; k++){
			if(primeArray[k]==1){
				primeNumberList.add(k);
			}
		}
		
		return primeNumberList;
	}
	
	/**
	 * Method to generate Nth Prime Number
	 * @param n
	 * @return
	 */
	public int generateNthPrime(int n) throws Exception{
		ArrayList<Integer> primeNumberList = new ArrayList<Integer>();
		
		if(n==1){
			return 2;
		}else if(n==2){
			return 3;
		}
		
		primeNumberList.add(2);
		primeNumberList.add(3);
		
		int number = 5;
		while(primeNumberList.size() != n){
			boolean isPrime = true;
			for(int i=0;primeNumberList.get(i)<=Math.sqrt(number);i++){
				if(number%primeNumberList.get(i) == 0){
					isPrime = false;
					break;
				}
				
			}
			if(isPrime){
				primeNumberList.add(number);
			}
			number = number+2;
		}
		
		
		if(primeNumberList.size() == n){
			return primeNumberList.get(primeNumberList.size()-1);
		}else{
			return 0;
		}
		
	}
	
	/**
	 * Method to obtain a starting list of prime numbers from similar previous queries
	 * @param searchResults
	 * @param range
	 * @return
	 */
	public ArrayList<Integer> getStartingList(TreeMap<Integer, ArrayList<Integer>> searchResults, int range) throws Exception{
		
		NavigableMap<Integer, ArrayList<Integer>> map = searchResults;
		Entry<Integer, ArrayList<Integer>> entry = map.lastEntry();
		
		int maxKey = 0;
		if(entry!=null){
			maxKey = entry.getKey();
		}
		
		if(searchResults.containsKey(maxKey)){
			System.out.println("List selected: "+ searchResults.get(maxKey));
			return searchResults.get(maxKey);
		}else if(maxKey==0){
			return null;
		}else{
			return null;
		}
		
	}
	
	/**
	 * Generate prime numbers with a starting list obtained from previous queries
	 * @param startingList
	 * @param range
	 * @return
	 */
	public ArrayList<Integer> generatePrimeHistorical(ArrayList<Integer> startingList, int range){
		
		ArrayList<Integer> primeNumberList = new ArrayList<Integer>();
		
		int[] primeArray = new int[range+1];
		for(int i=0; i< range+1; i++){
			primeArray[i]= 1;
		}
		
		for(Integer primeNum: startingList){
			primeNumberList.add(primeNum);
			
			for(int j=2; primeNum*j <=range ; j++){
				primeArray[primeNum*j] = 0;
			}
			
		}
		
		int startingNumber = primeNumberList.get(primeNumberList.size()-1)+1;
		
		if(startingNumber < range){
			int upperLimit = (int) Math.sqrt(range);
			
			primeArray[0]=0;
			primeArray[1]=0;
			
			for(int k=startingNumber; k<=upperLimit; k++){
				if(primeArray[k] == 1){
					for(int j=2; k*j <=range ; j++){
						primeArray[k*j] = 0;
					}
				}
			}
			
			for(int k=startingNumber;k<primeArray.length; k++){
				if(primeArray[k]==1){
					primeNumberList.add(k);
				}
			}
		}else{
			startingNumber = 2;
			primeNumberList.clear();
			for(int k=startingNumber;k<primeArray.length; k++){
				if(primeArray[k]==1){
					primeNumberList.add(k);
				}
			}
			
		}
		
		return primeNumberList;
		
	}

}
