package com.example.demo.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {

	@Autowired
	DashboardService dashBoardRepository;

	@PostMapping(value = "/save")
	ResponseEntity<DashBoardModel> Dashboardsave(@RequestBody DashBoardModel dashBoardModel) throws Exception {
		return ResponseEntity.ok(dashBoardRepository.MachineSave(dashBoardModel));

	}
	@GetMapping(value = "/findall")
		ResponseEntity<List<DashBoardModel>> findall(@ModelAttribute DashBoardModel dash)
		{
			return ResponseEntity.ok(dashBoardRepository.findall(dash));
		}
 }
