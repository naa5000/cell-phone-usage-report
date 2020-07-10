/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import model.CellPhone;
import model.CellPhoneUsageByMonth;

/**
 *
 * @author nedar
 */
public class CsvParser {

    public List<CellPhone> parseCellPhonesFromCSV(String fileName) throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader oReader = csvMapper.reader(CellPhone.class).with(schema);
        List<CellPhone> cellPhones = new ArrayList<>();

        try (Reader reader = new FileReader(fileName)) {
            MappingIterator<CellPhone> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                CellPhone current = mi.next();
                cellPhones.add(current);
                //Debug Statement
                System.out.println(current);
            }
        }

        return cellPhones;
    }

    public List<CellPhoneUsageByMonth> parseCellPhoneUsageReportsFromCSV(String fileName) throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader oReader = csvMapper.reader(CellPhoneUsageByMonth.class).with(schema);
        List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth = new ArrayList<>();

        try (Reader reader = new FileReader(fileName)) {
            MappingIterator<CellPhoneUsageByMonth> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                CellPhoneUsageByMonth current = mi.next();
                cellPhoneUsagesByMonth.add(current);
                //Debug Statement
                System.out.println(current);
            }
        }

        return cellPhoneUsagesByMonth;
    }

}
