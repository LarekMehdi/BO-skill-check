package fr.perso.skillcheck.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fr.perso.skillcheck.constants.SortOrder;
import jakarta.validation.constraints.Min;

public class GenericFilter {

    @Min(value = 1, message = "La limite doit être supérieure à 0")
    private Integer         limit;

    @Min(value = 0, message = "Le offset ne peut pas être négatif")
    private Integer         offset;

    private String          sortBy;

    private SortOrder       sortOrder;    
    
    public GenericFilter() {}

    public GenericFilter(Integer limit, Integer offset, String sortBy, SortOrder sortOrder) {
        this.limit = limit;
        this.offset = offset;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    public GenericFilter(Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    /** LIMIT **/

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public boolean hasLimit() {
        return !UtilEntity.isEmpty(this.limit);
    }

    /** OFFSET **/

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public boolean hasOffset() {
        return !UtilEntity.isEmpty(this.offset);
    }

    /** SORT BY **/

    public String getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean hasSortBy() {
        return !UtilEntity.isEmpty(this.sortBy);
    }

    /** SORT ORDER **/

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean hasSortOrder() {
        return !UtilEntity.isEmpty(this.sortOrder);
    }

    public boolean isSortOrderAsc() {
        return this.hasSortOrder() && this.getSortOrder() == SortOrder.ASC;
    }

    public boolean isSortOrderDesc() {
        return this.hasSortOrder() && this.getSortOrder() == SortOrder.DESC;
    }

    /** METHODS **/

    public void initGenericFilterIfNeeded() {
        if (!this.hasLimit()) this.limit = 10;
        if (!this.hasOffset()) this.offset = 0;
    }

    public Pageable toPageable() {
        Sort sort = Sort.unsorted();
        if (this.hasSortBy()) {
            sort = Sort.by(
                this.isSortOrderDesc() ? Sort.Direction.DESC : Sort.Direction.ASC,
                this.getSortBy()
            );
        }
        return PageRequest.of(this.getPage(), this.limit, sort);
    }

    public Integer getPage() {
        return this.offset / this.limit;
    }


}
