package fr.perso.skillcheck.files.imports;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.utils.UtilExcel;

@Service
public class TestImportService {

    public List<Test> importExcel(InputStream inputStream, UserPrincipal user) {
        return UtilExcel.importExcel(inputStream, user);
    }
    
}
