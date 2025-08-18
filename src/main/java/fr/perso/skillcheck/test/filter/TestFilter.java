package fr.perso.skillcheck.test.filter;

import fr.perso.skillcheck.constants.SortOrder;
import fr.perso.skillcheck.utils.GenericFilter;

public class TestFilter extends GenericFilter{
    
    public TestFilter() {
        super();
    }

    public TestFilter(Integer limit, Integer offset, String sortBy, SortOrder sortOrder) {
        super(limit, offset, sortBy, sortOrder);
    }

    public TestFilter(Integer limit, Integer offset) {
        super(limit, offset);
    }
}
