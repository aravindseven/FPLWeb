package com.fpl.core.paging;

import java.util.Collection;

import com.fpl.paging.IPagingData;

@SuppressWarnings("serial")
public class PagingData implements IPagingData {

    private final Collection pageResultList;
    private final int pageSize;
    private final int pagesPerBlock;
    private final int pageNumber;
    private int totalRecords = 0;

    public PagingData(final int pageSize, final int pageNumber, final int totalRecords, final int pagesPerBlock, final Collection currentList) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalRecords = totalRecords;
        this.pagesPerBlock = pagesPerBlock;
        this.pageResultList = currentList;
    }
    
    /**
     * Return the current page number. Page numbering starts with 1. 
     * 
     * @return - current page number
     */
    public Integer getCurrentPageNo() {
        return pageNumber;
    }

    /**
     * Return the last page number for the current block.
     * 
     * @return - ending page number for the current block
     */
    public Integer getEndingPageNo() {
        final int toPage = getBlockNumber() * pagesPerBlock;
        return toPage < getTotalPages() ? toPage : getTotalPages();
    }

    /**
     * Return the first block starting page number.
     * 
     * @return - first block first page number.
     */
    public Integer getFirstBlockFirstPage() {
        return Integer.valueOf(1);
    }

    /**
     * Return the last block starting page number.
     * 
     * @return - last block first page number.
     */
    public Integer getLastBlockFirstPage() {
        final int totalPages = getTotalPages();
        int totalBlocks = totalPages / pagesPerBlock;
        if ((totalPages % pagesPerBlock) > 0) {
            totalBlocks++;
        }
        return ((totalBlocks - 1) * pagesPerBlock) + 1;
    }

    /**
     * Return the next block starting page number.
     * 
     * @return - next block first page number.
     */
    public Integer getNextBlockFirstPage() {
        return hasSuccessive() ? (getBlockNumber() * pagesPerBlock) + 1 : 1;
    }

    /**
     * Return a collection representing the current page. 
     * 
     * @param <ResultType>
     * 
     * @return - page element list
     */
    public <ResultType> Collection<ResultType> getPageResult() {
        return pageResultList;
    }

    /**
     * Return the previous block starting page number.
     * 
     * @return - previous block first page number.
     */
    public Integer getPrevBlockFirstPage() {
        return hasPrevious() ? getStartingPageNo() - pagesPerBlock : 1;
    }

    /**
     * Return the first page number for the current block.
     * 
     * @return - starting page number for the current block
     */
    public Integer getStartingPageNo() {
        return ((getBlockNumber() - 1) * pagesPerBlock) + 1;
    }

    /**
     * Return the number of pages for the current source list. 
     * 
     * @return - total page count.
     */
    public Integer getTotalPages() {
        return (totalRecords / pageSize) + ((totalRecords % pageSize) > 0 ? 1 : 0);
    }

    /**
     * Return the total number of elements in the current page. 
     * 
     * @return - current page total number of element
     */
    public Integer getpageSize() {
        return pageSize;
    }

    /**
     * Return if the previous block is available. 
     * 
     * @return - Return if the current page is the first one. 
     */
    public Boolean hasPrevious() {
        return getStartingPageNo() != 1;
    }

    /**
     * Return if the next block is available. 
     * 
     * @return - Return if the current page is the last one. 
     */
    public Boolean hasSuccessive() {
        return getEndingPageNo() < getTotalPages();
    }
    
    /**
     * Used to calculate and return the block number.
     * 
     * @return - calculated block number
     */
    private int getBlockNumber() {
        int blockNumber = (pageNumber / pagesPerBlock);
        if ((pageNumber % pagesPerBlock) > 0) {
            blockNumber++;
        }
        return blockNumber;
    }
    
    @Override
    public String toString() {
        return String.format("pageSize = %s, Page No = %s, Pages PerBlock = %s, Total Records = %s, Current Page Size = %s, Total Pages", 
                String.valueOf(pageSize),String.valueOf(pageNumber),String.valueOf(pagesPerBlock),String.valueOf(totalRecords),(pageResultList != null ? String.valueOf(pageResultList.size()) : "null"), String.valueOf(getTotalPages()));
    }
}


