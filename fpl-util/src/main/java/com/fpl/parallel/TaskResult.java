package com.fpl.parallel;

public class TaskResult<Result> {

    private final String taskName;
    private final Result result;
    
    public TaskResult(final String taskName, final Result result) {
        this.taskName = taskName;
        this.result = result;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "[Result: "+result+", taskName: "+ taskName +"]";
    }
}


