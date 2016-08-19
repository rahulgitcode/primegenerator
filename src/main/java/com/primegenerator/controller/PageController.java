package com.primegenerator.controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.primegenerator.model.FormData;
import com.primegenerator.service.PrimeGenerator;

@Controller
public class PageController extends WebMvcConfigurerAdapter {
	
	@Autowired
	PrimeGenerator primeGenerator;
	
	@RequestMapping(value="/generatePrime", method=RequestMethod.GET)
    public String showPrimeForm(FormData formData) {
        return "primegenerator";
    }

    @RequestMapping(value="/generatePrime", method=RequestMethod.POST)
    public String getPrimeNumbers(@Valid@ModelAttribute(value="formData") FormData formData, BindingResult bindingResult,
    														HttpServletRequest request, Model model) {

        if (bindingResult.hasErrors()) {
            return "primegenerator";
        }
        
        try{
			String n = formData.getInputNum();
			
			TreeMap<Integer, ArrayList<Integer>> searchResults = (TreeMap<Integer, ArrayList<Integer>>) request.getSession().getAttribute("previousSearch");
			if(searchResults == null){
				searchResults = new TreeMap<Integer, ArrayList<Integer>>();
			}
			
			ArrayList<Integer> startingList = new ArrayList<Integer>();
			ArrayList<Integer> primeNumberList = new ArrayList<Integer>();
			
			startingList = primeGenerator.getStartingList(searchResults, Integer.parseInt(n));
			
			if(startingList == null){
				primeNumberList = primeGenerator.generatePrime(Integer.parseInt(n));
			}else{
				primeNumberList = primeGenerator.generatePrimeHistorical(startingList, Integer.parseInt(n));
			}
			
			searchResults.put(Integer.parseInt(n), primeNumberList);
			request.getSession().setAttribute("previousSearch", searchResults);
			
			//Print previous queries
			TreeMap<Integer, ArrayList<Integer>> savedResults = (TreeMap<Integer, ArrayList<Integer>>) request.getSession().getAttribute("previousSearch");
			Set<Integer> keySet = savedResults.keySet();
			for(Integer key: keySet){
				System.out.println(savedResults.get(key));
			}
					
			FormData form = new FormData();
			
			form.setInputNum(n);
			form.setPrimeNumbers(primeNumberList.toString());
			model.addAttribute("formData", form);
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		return "primegenerator";
    }
    
    @RequestMapping(value="/generateNthPrime", method=RequestMethod.GET)
    public String showNPrimeForm(FormData formData) {
        return "nthprime";
    }
    
    @RequestMapping(value="/generateNthPrime", method=RequestMethod.POST)
	public String getNthPrime(@Valid@ModelAttribute(value="formData") FormData formData, BindingResult bindingResult,
			HttpServletRequest request, Model model){
		try{
			
			if(bindingResult.hasErrors()){
				return "nthprime";
			}
			
			String n = formData.getInputNum();
			Integer output = primeGenerator.generateNthPrime(Integer.parseInt(n));
			
			FormData form = new FormData();
			
			form.setInputNum(n);
			form.setPrimeNumbers(output.toString());
			model.addAttribute("formData", form);
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
			
		return "nthprime";
		
	}

}
