package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	private List<PilotModel> archivePilot;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void deletePilot(long id) {
		pilotDb.deleteById(id);
	}
	
	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}
	/**
	 * cari dokumentasi jpa untuk update dll
	 * view itu get bukan post
	 * get: db ke frontend
	 * post: frontend ke db
	 */
}
