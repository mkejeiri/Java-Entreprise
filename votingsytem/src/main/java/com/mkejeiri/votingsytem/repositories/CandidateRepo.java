package com.mkejeiri.votingsytem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mkejeiri.votingsytem.entity.Candidate;

public interface CandidateRepo  extends JpaRepository<Candidate, Integer>{
	public Candidate findById(Long id);
}