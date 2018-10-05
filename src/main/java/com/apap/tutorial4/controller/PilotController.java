package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(String licenseNumber, Model model) {
		PilotModel archieve = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(archieve == null) {
			return "error";
		}
		List<FlightModel> flightList = archieve.getPilotFlight();
		
		model.addAttribute("pilot", archieve);
		model.addAttribute("flightList", flightList);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> temp = pilotService.getPilotList();
		
		model.addAttribute("listPilot", temp);
		for (int i=0; i<temp.size(); i++) {
			PilotModel archieve = pilotService.getPilotDetailByLicenseNumber(temp.get(i).getLicenseNumber());
			List<FlightModel> flightList = archieve.getPilotFlight();
			model.addAttribute("flightList", flightList);
		}
		model.addAttribute("pilot", temp);
		return "view-all";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "id") Long id, Model model) {
		pilotService.deletePilot(id);
		return "delete";
	}
}
