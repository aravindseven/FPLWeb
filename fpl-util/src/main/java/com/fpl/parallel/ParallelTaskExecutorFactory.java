package com.fpl.parallel;

import com.fpl.parallel.internal.ParallelTaskExecutorBuilderImpl;

public class ParallelTaskExecutorFactory {

    private static ParallelTaskExecutorFactory factory;
    private ParallelTaskExecutorBuilder executorBuilder;
    
    private ParallelTaskExecutorFactory() {}
    
    public static ParallelTaskExecutorFactory getInstance() {
        if(factory == null) {
            synchronized (ParallelTaskExecutorFactory.class) {
                if(factory == null) {
                    factory = new ParallelTaskExecutorFactory();
                    factory.setExecutorBuilder(new ParallelTaskExecutorBuilderImpl());
                }
            }
        }
        return factory;
    }
    
    public ParallelTaskExecutor getParallelTaskExecutor(final ParallelTaskExecutionType executionType) {
        ParallelTaskExecutor taskExecutor = null;
        if(ParallelTaskExecutionType.SIMPLE_EXECUTOR.equals(executionType)) {
            taskExecutor = getExecutorBuilder().getSimpleParallelTaskExecutor();
        }
        return taskExecutor;
    }

    /**
     * @return the executorBuilder
     */
    protected ParallelTaskExecutorBuilder getExecutorBuilder() {
        return executorBuilder;
    }
    
    /**
     * @param executorBuilder the executorBuilder to set
     */
    public void setExecutorBuilder(final ParallelTaskExecutorBuilder executorBuilder) {
        this.executorBuilder = executorBuilder;
    }
}


