package com.mkejeiri.votingsytem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mkejeiri.votingsytem.entity.Citizen;

public interface CitizenRepo  extends JpaRepository<Citizen,Integer>{
	public Citizen findByName(String name);
}