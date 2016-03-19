package com.fpl.core.paging;

import java.util.Collection;

import com.fpl.FPLException;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.paging.IPagingData;
import com.fpl.paging.IPagingProcess;
import com.fpl.paging.support.IPagingTemplate;
import com.fpl.paging.support.PagingSpecification;

@SuppressWarnings("serial")
public class PagingProcess implements IPagingProcess {

    private final IPagingTemplate pageRequest;
    private int pageSize;
    private int pagesPerBlock;
    private int totalSize;
    private int presentPageNo;
    private int totalPage;
    
    public PagingProcess(final IPagingTemplate pageRequest) {
        this.pageRequest = pageRequest;
    }
    
    @Override
	public IPagingData getFirstPage(final PagingSpecification specification) {
        specification.setPageNo(Integer.valueOf(1));
        setSize();
        setPageInfo(specification);
        calculateTotalpages();
        return getPageData(1,pageSize);
    }

    @Override
	public IPagingData getCurrentPage(final boolean isRefresh) {
        if(isRefresh) {
            setSize();
            calculateTotalpages();
        }
        return getPageData(presentPageNo,pageSize);
    }

    @Override
	public IPagingData getPage(final PagingSpecification specification) {
        setPageInfo(specification);
        if(specification.isRefresh()) {
            setSize();
            calculateTotalpages();
        }
        return getPageData(presentPageNo,pageSize);
    }

    private void setPageInfo(final PagingSpecification specification) {
        this.pageSize = specification.getPageSize();
        this.pagesPerBlock = specification.getPagesPerBlock();
        this.presentPageNo = specification.getPageNo();
    }

    private <ResultType, ReturnType> IPagingData getPageData(int pageNo, final int pageRecordCount) {
        IPagingData page = null;
        if(totalSize > 0 ) {
            pageNo = pageNo > 0 ? pageNo : 1;
            final Collection<ResultType> searchList = pageRequest.getPageResult(pageNo,pageRecordCount);
            page = new PagingData(pageSize, presentPageNo, totalSize, pagesPerBlock, searchList);
        } else {
            throw new FPLException(FPLCommonErrorMessage.NO_RECORD_FOUND);
        }
        return page;
    }

    private void setSize() {
        this.totalSize = pageRequest.getTotalRecord();
    }

    private void calculateTotalpages() {
        this.totalPage = (this.totalSize / this.pageSize) + ((this.totalSize % this.pageSize) > 0 ? 1 : 0);
        this.presentPageNo = this.presentPageNo > this.totalPage ? this.totalPage : this.presentPageNo;
    }
}
