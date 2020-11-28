package com.personal.etfComparator.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.etfComparator.components.CSVGather;

@Service
public class SvcLayerImpl implements SvcLayer{

	@Autowired
	CSVGather csvGather;
	
	public void compareatorStart() {
		csvGather.CsvGet();
	}

}
