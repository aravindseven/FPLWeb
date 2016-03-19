package com.fpl.parallel.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fpl.parallel.Task;

public class SimpleConcurrentExecutor extends AbstractParallelTaskExecutor {
    
    @Override
    protected <Result> Collection<Result> executeInternal(final List<? extends Task<? extends Result>> taskList) {
        final ExecutorService service = Executors.newFixedThreadPool(4);
        try {
            final CompletionService<Result> completionService = new ExecutorCompletionService<Result>(service);
            for(final Task<?> task : taskList) {
                completionService.submit(new Callable<Result>() {
                    @Override
                    public Result call() throws Exception {
                        return (Result) task.runExecution();
                    }
                });
            }
            
            final Collection<Result> returnColl = new ArrayList<Result>();
            for(int index = 0; index < taskList.size(); index++) {
                try {
                    final Result taskExecutionResult = completionService.take().get();
                    returnColl.add(taskExecutionResult);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                } catch (final ExecutionException e) {
                    e.printStackTrace();
                }
                
            }
            return returnColl;
        } finally {
            service.shutdown();
        }
    }    
}