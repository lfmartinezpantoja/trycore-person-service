package com.trycore.person.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trycore.commons.dto.PlanetResponseDTO;

@FeignClient(name = "planet-service", url = "http://localhost:8081")
public interface PlanetService {

	@GetMapping("planets/{idPlanet}")
	public ResponseEntity<PlanetResponseDTO> getPlanet(@PathVariable Long idPlanet);
}