package com.fpl.paging;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p> 
 * IPagingData is a simple state holder for handling lists of objects, separating them into pages. 
 * This is mainly targeted at usage in web UIs. Typically, an instance will be instantiated with a list 
 * of paging data, put into the request, and exported as model. <p> 
 * 
 * <p> The properties can all be set/get programmatically, but the most common way will be 
 * data calculation, i.e. calculating the data from total record fetched. The getters will 
 * mainly be used by the view. </p>
 *  
 * @author Murali
 */
public interface IPagingData extends Serializable {

    /**
     * Return the first page number for the current block.
     * 
     * @return - starting page number for the current block
     */
    Integer getStartingPageNo();

    /**
     * Return the last page number for the current block.
     * 
     * @return - ending page number for the current block
     */
    Integer getEndingPageNo();

    /**
     * Return the current page number. Page numbering starts with 1. 
     * 
     * @return - current page number
     */
    Integer getCurrentPageNo();

    /**
     * Return the next block starting page number.
     * 
     * @return - next block first page number.
     */
    Integer getNextBlockFirstPage();

    /**
     * Return the first block starting page number.
     * 
     * @return - first block first page number.
     */
    Integer getFirstBlockFirstPage();

    /**
     * Return the last block starting page number.
     * 
     * @return - last block first page number.
     */
    Integer getLastBlockFirstPage();

    /**
     * Return the previous block starting page number.
     * 
     * @return - previous block first page number.
     */
    Integer getPrevBlockFirstPage();

    /**
     * Return the number of pages for the current source list. 
     * 
     * @return - total page count.
     */
    Integer getTotalPages();

    /**
     * Return the total number of elements in the current page. 
     * 
     * @return - current page total number of element
     */
    Integer getpageSize();

    /**
     * Return if the next block is available. 
     * 
     * @return - Return if the current page is the last one. 
     */
    Boolean hasSuccessive();

    /**
     * Return if the previous block is available. 
     * 
     * @return - Return if the current page is the first one. 
     */
    Boolean hasPrevious();

    /**
     * Return a collection representing the current page. 
     * 
     * @param <ResultType>
     * 
     * @return - page element list
     */
    <ResultType> Collection<ResultType> getPageResult();
}


