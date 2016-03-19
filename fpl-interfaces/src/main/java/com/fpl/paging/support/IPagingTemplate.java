package com.fpl.paging.support;

import java.io.Serializable;
import java.util.Collection;

public interface IPagingTemplate extends Serializable {

    int getTotalRecord();
    
    <PageResult> Collection<PageResult> getPageResult(int pageNo, int pageSize);
}


