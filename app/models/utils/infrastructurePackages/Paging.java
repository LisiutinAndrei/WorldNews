package models.utils.infrastructurePackages;

public class Paging {

    public Paging() {
        this(0, 0);
    }

    public Paging(int page, int itemsPerPage) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
    }

    public int page;
    public int itemsPerPage;
}
