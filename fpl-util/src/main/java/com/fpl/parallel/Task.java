package com.fpl.parallel;

public abstract class Task<Result> {

    private final String taskName;
    private TaskResult<Result> taskResult;

    /**
     * This constructor defines the name of this Task
     * 
     * @param description the description of this Task
     */
    public Task(final String taskName) {
        this.taskName = taskName;
    }

    /**
     * This method must be overridden to define the execution of the task that can be executed parallely. This method should return the result of the execution as an 
     * Object or null if it is a void execution.
     * 
     * @return the result of the task execution
     * 
     * @throws Throwable any exceptions thrown during job execution
     */
    protected abstract Result execute();

    /**
     * This method should be called only internally by ParallelExecution framework!
     */
    public final Result runExecution() {
        Result result = null;
        try {
            System.out.println("Executing Task: "+this);
            result = execute();
            setTaskResult(new TaskResult<Result>(taskName,result));
            System.out.println("Finished Task: "+this);
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return the taskResult
     */
    public TaskResult<Result> getTaskResult() {
        return taskResult;
    }
    
    /**
     * @param taskResult the taskResult to set
     */
    private void setTaskResult(final TaskResult<Result> taskResult) {
        this.taskResult = taskResult;
    }

    /**
     * This method returns the result of the execution, null in case of void execution.
     * 
     * @return the result of job execution, null if it is a void execution.
     * 
     */
    public final Result getExecutionResult() {
        return getTaskResult().getResult();
    }

    @Override
    public final String toString() {
        return "[Task: "+this.taskName+"]";
    }

}

