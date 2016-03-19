package com.fpl.paging.support;

public class PagingSpecification {
    
    private Integer pageNo;
    private Integer pageSize;
    private Integer pagesPerBlock;
    private boolean refresh;
    
    public PagingSpecification() {
    	/** Empty Constructor*/
    }
    
    public PagingSpecification(final Integer pageNo, final Integer pageSize, final Integer pagesPerBlock, final boolean refresh) {
    	this.pageNo = pageNo;
    	this.pageSize = pageSize;
    	this.pagesPerBlock = pagesPerBlock;
    	this.refresh = refresh;
    }
    
    public static PagingSpecification getFirstPagingSpecification(final Integer pageSize, final Integer pagesPerBlock) {
        final PagingSpecification specification = new PagingSpecification();
        specification.setPageSize(pageSize);
        specification.setPagesPerBlock(pagesPerBlock);
        specification.setPageNo(Integer.valueOf(1));
        specification.setRefresh(true);
        return specification;
    }
    
    public static PagingSpecification getDefaultPagingSpecification(final Integer pageNo) {
        final PagingSpecification specification = new PagingSpecification();
        specification.setPageSize(10);
        specification.setPagesPerBlock(10);
        specification.setPageNo(pageNo);
        specification.setRefresh(true);
        return specification;
    }
    
    /**
     * @return the pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }
    
    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(final Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }
    
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @return the pagesPerBlock
     */
    public Integer getPagesPerBlock() {
        return pagesPerBlock;
    }
    
    /**
     * @param pagesPerBlock the pagesPerBlock to set
     */
    public void setPagesPerBlock(final Integer pagesPerBlock) {
        this.pagesPerBlock = pagesPerBlock;
    }
    
    /**
     * @return the refresh
     */
    public Boolean isRefresh() {
        return refresh;
    }
    
    /**
     * @param refresh the refresh to set
     */
    public void setRefresh(final Boolean refresh) {
        this.refresh = refresh;
    }
    
    @Override
    public String toString() {
        return String.format("pageNo = %s, pagesPerBlock = %s, pageSize = %s, refresh = %s", 
                String.valueOf(pageNo),String.valueOf(pagesPerBlock),String.valueOf(pageSize), String.valueOf(refresh));
    }
}


