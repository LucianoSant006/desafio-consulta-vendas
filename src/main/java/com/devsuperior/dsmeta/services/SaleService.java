package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	public Page<SaleReportDTO> SummaryBySalesperson(String name, String startDate, String endDate, Pageable pageable) {

		LocalDate end = (endDate == null || endDate.isEmpty())  ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(endDate);
		LocalDate start = (startDate == null || startDate.isEmpty())  ? end.minusYears(1L) :  LocalDate.parse(startDate);

		return repository.findSummaryBySalesperson(name,start,end,pageable);


	}
}
