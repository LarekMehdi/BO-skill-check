package fr.perso.skillcheck.utils;


public class QuerySearch {
    
    private String      label;

    public QuerySearch() {}

    public QuerySearch(String label) {
        this.label = label;
    }

     /** LABEL **/

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean hasLabel() {
        return !UtilEntity.isEmpty(this.label);
    }

    
}
