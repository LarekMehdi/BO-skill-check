package fr.perso.skillcheck.files.exports;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.utils.UtilExcel;

@Service
public class TestExportService {
    
    public byte[] exportListToExcel(List<TestExportDto> dtos) {
        return UtilExcel.exportTestList(dtos);
    }
}
