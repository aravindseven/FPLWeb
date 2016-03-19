package com.fpl.paging;

import java.io.Serializable;

import com.fpl.paging.support.PagingSpecification;

public interface IPagingProcess extends Serializable {

    IPagingData getFirstPage(PagingSpecification pagingSpecification);
    
    IPagingData getPage(PagingSpecification pagingSpecification);
    
    IPagingData getCurrentPage(boolean isRefresh);
}


