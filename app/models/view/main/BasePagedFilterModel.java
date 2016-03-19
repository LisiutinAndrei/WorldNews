package models.view.main;

import models.utils.infrastructurePackages.Paging;
import play.data.Form;

import java.util.ArrayList;
import java.util.List;

public class BasePagedFilterModel<T, F> {
    public BasePagedFilterModel(List<T> list, int count, Paging paging, Form<F> form) {
        this.list = list;
        this.count = count;
        this.paging = paging;
        this.form = form;
    }

    public List<T> list;
    public int count;
    public Paging paging;
    public Form<F> form;

    public Integer getTotalPages() {
        int totalPages = (int) Math.ceil((double) this.count / this.paging.itemsPerPage);
        return totalPages;
    }

    public List<Integer> getPageNumbers() {
        List<Integer> pageNumbers = new ArrayList<Integer>();
        int totalPages = this.getTotalPages();
        if (totalPages > 10) {
            int minPage = (this.paging.page - 5) <= 0 ? 1 : (this.paging.page - 5);
            int maxPage = minPage + 10;
            if (minPage > 1) {
                pageNumbers.add(1);
            }
            for (int i = minPage; i <= minPage + 10; i++) {
                pageNumbers.add(i);
            }
        } else {
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
        }
        return pageNumbers;
    }
}
