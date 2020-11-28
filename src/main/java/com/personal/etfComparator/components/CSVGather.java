package com.personal.etfComparator.components;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.personal.etfComparator.objects.CSV;

@Component
public class CSVGather {

	public void CsvGet() {
		Path oldFile = FileSystems.getDefault().getPath(Your old file location here);
		Path newFile = FileSystems.getDefault().getPath(Your new file location here);
		try {
			Files.copy(newFile, oldFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			URL url = new URL(
					"https://ark-funds.com/wp-content/fundsiteliterature/csv/ARK_INNOVATION_ETF_ARKK_HOLDINGS.csv");
			File file = new File(newFile.toString());
			FileUtils.copyURLToFile(url, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			CSVReader reader = new CSVReaderBuilder(new FileReader(newFile.toString()))
					.withSkipLines(1). // Skiping firstline as it is header
					build();
			List<CSV> csvObj = reader.readAll().stream().map(data -> {
				CSV csv = new CSV();
				if (!(data[0].isBlank() || data[1].isBlank())) {
					csv.setDate(data[0]);
					csv.setFund(data[1]);
					csv.setCompany(data[2]);
					csv.setTicker(data[3]);
					csv.setCusip(data[4]);
					csv.setShares(data[5]);
					csv.setMarketValue(data[6]);
					csv.setWeight(data[7]);
				}
				return csv;
			}).collect(Collectors.toList());
			
			
			reader = new CSVReaderBuilder(new FileReader(oldFile.toString())).withSkipLines(1). // Skiping
					build();
			List<CSV> csvObjOld = reader.readAll().stream().map(data -> {
				CSV csvOld = new CSV();
				if (!(data[0].isBlank() || data[1].isBlank())) {
					csvOld.setDate(data[0]);
					csvOld.setFund(data[1]);
					csvOld.setCompany(data[2]);
					csvOld.setTicker(data[3]);
					csvOld.setCusip(data[4]);
					csvOld.setShares(data[5]);
					csvOld.setMarketValue(data[6]);
					csvOld.setWeight(data[7]);
				} else {
					csvOld.setCompany("");
				}
				return csvOld;
			}).collect(Collectors.toList());

			for (int i = 0; i < csvObj.size(); i++) {
				if (!csvObjOld.get(i).getCompany().isBlank()) {
					System.out.println(csvObjOld.get(i).getCompany().isBlank());
					System.out.println(csvObjOld.get(i).getMarketValue());
					System.out.println(csvObjOld.get(i).getMarketValue().compareTo(csvObj.get(i).getMarketValue()));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
