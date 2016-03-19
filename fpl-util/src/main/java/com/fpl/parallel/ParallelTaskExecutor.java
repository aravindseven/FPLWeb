package com.fpl.parallel;

import java.util.Collection;
import java.util.List;

/**
 * Simple task executor interface that abstracts the execution of a Runnable. Implementations can use all sorts of different execution strategies, 
 * such as: synchronous, asynchronous, using a thread pool, and more.
 * 
 * @author Murali
 * 
 */
public interface ParallelTaskExecutor {

    /**
     * This method executes the specified list of task possibly parallely,the result of every task either can be retrieved through the Task themselves with 
     * getExecutionResult() method or can be retrieved from the returned list.
     * 
     * The returned list contains all the task execution results
     * 
     * @param taskList - the list of tasks to be executed possibly parallely
     * 
     * @return the execution task result list
     * 
     */
    <Result> Collection<Result> executeTask(List<? extends Task<? extends Result>> taskList);
}


