package com.fpl.parallel.internal;

import com.fpl.parallel.ParallelTaskExecutor;
import com.fpl.parallel.ParallelTaskExecutorBuilder;

public class ParallelTaskExecutorBuilderImpl implements ParallelTaskExecutorBuilder {
    
    private ParallelTaskExecutor simpleParallelTaskExecutor;
    
    @Override
    public ParallelTaskExecutor getSimpleParallelTaskExecutor() {
        if(simpleParallelTaskExecutor == null) {
            synchronized (ParallelTaskExecutorBuilderImpl.class) {
                if(simpleParallelTaskExecutor == null) {
                    simpleParallelTaskExecutor = new SimpleConcurrentExecutor();
                }
            }
        }
        return simpleParallelTaskExecutor;
    }
}


