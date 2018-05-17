package com.mkejeiri.votingsytem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkejeiri.votingsytem.entity.Candidate;
import com.mkejeiri.votingsytem.entity.Citizen;
import com.mkejeiri.votingsytem.repositories.CandidateRepo;
import com.mkejeiri.votingsytem.repositories.CitizenRepo;
//@RestController
@Controller
public class VotingController {
	public final Logger logger = Logger.getLogger(VotingController.class);

	@Autowired
	CitizenRepo citizenRepo;
	
	@Autowired
	CandidateRepo candidateRepo;
	
/*	@RequestMapping("/doAction")
	public String doAction() {
		Citizen t = new Citizen((long)2, "John");
		Candidate c = new Candidate((long)2, "John Doe"); 
		citizenRepo.save(t);
		candidateRepo.save(c);
		return "Success";
	}*/
	
	
	@RequestMapping("/")
	public String goToVote() {	
		logger.info("Returning vote.html file");
		logger.warn("Returning vote.html file");
		logger.error("Returning vote.html file");
		return "vote.html";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String name, org.springframework.ui.Model model,HttpSession session)
	{ 
		
		Citizen citizen = 	citizenRepo.findByName(name);
		if (citizen == null) {
			return "/voterNotFound.html";
		}
		logger.info("Putting "+citizen.getName()+" into session");
		session.setAttribute("citizen", citizen);				
		if (!citizen.getHasVoted()) {
			
			List<Candidate> candidates = candidateRepo.findAll();		
			
			model.addAttribute("candidates",candidates);
			model.addAttribute("citizen",citizen);
			
			return "/performVote.html";		
		}else {
			logger.info(citizen.getName()+" has already voted");
			model.addAttribute("alreadyVoted",citizen);
			return "/alreadyVoted.html";
		}		 
	}
	
	@RequestMapping("/voteFor")
	public String voteFor(@RequestParam Long id, org.springframework.ui.Model model,HttpSession session) {	
		Citizen citizen =(Citizen)session.getAttribute("citizen");
		
		
		Candidate candidate = candidateRepo.findById(id);
		if (candidate.getNumberOfVotes() == null) {
			candidate.setNumberOfVotes(1);
		} 
		if (!citizen.getHasVoted()) {
			candidate.setNumberOfVotes(candidate.getNumberOfVotes()+1);
			citizen.setHasVoted(true);			
			citizenRepo.save(citizen);
			logger.info("make "+citizen.getName()+" already voted in DB!");
			candidateRepo.save(candidate);	
			logger.info(candidate.getName()+" one extra vote & saved into DB");
			model.addAttribute("VotedCandidate", candidate);		
			return "voted.html";
		} else {
			model.addAttribute("alreadyVoted",citizen);
			return "/alreadyVoted.html";
		}
		
	}
	
}
	