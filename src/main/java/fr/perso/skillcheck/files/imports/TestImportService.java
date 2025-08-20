package fr.perso.skillcheck.files.imports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.utils.UtilExcel;

@Service
public class TestImportService {

    public List<Test> importExcel(InputStream inputStream, UserPrincipal user) {
        List<TestExportDto> dtos = UtilExcel.importExcel(inputStream, user);

        // save des tests
        List<Test> tests = new ArrayList<>();

        // saves des questions

        // saves des answers

        // return test
        return new ArrayList<>();
    }
    
}
