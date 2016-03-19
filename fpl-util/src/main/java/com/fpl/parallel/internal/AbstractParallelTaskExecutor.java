package com.fpl.parallel.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpl.parallel.ParallelTaskExecutor;
import com.fpl.parallel.Task;

public abstract class AbstractParallelTaskExecutor implements ParallelTaskExecutor {

	@Override
	public <Result> Collection<Result> executeTask(final List<? extends Task<? extends Result>> taskList) {
		final String callerClassName = getCallerClassName();
		final long startTime = System.currentTimeMillis();
		final Collection<Result> results = executeInternal(taskList);
		getUsageStatistics().addUsage(callerClassName,System.currentTimeMillis()-startTime);
		return results;
		//return getResultList(taskList);
	}

	/*@SuppressWarnings("unchecked")
   private <Result> Collection<Result> getResultList(final List<? extends Task<? extends Result>> taskList) {
        return CollectionUtils.collect(taskList, new Transformer() {
            @Override
            public Result transform(final Object element) {
                return (Result) ((Task<?>)element).getExecutionResult();
            }
        });
    }*/

	protected abstract <Result> Collection<Result> executeInternal(List<? extends Task<? extends Result>> taskList);


	// usage static
	private final UsageStatistics usageStatistics = new UsageStatistics();

	protected UsageStatistics getUsageStatistics() {
		return this.usageStatistics;
	}
	
	public class UsageStatistics {

		private final Map<String, CallerUsage> usageMap = new HashMap<String, CallerUsage>(1000);

		public void addUsage(final String callerClassName, final long usageTime) {
			if (!this.usageMap.containsKey(callerClassName)) {
				synchronized (this.usageMap) {
					if (!this.usageMap.containsKey(callerClassName)) {
						this.usageMap.put(callerClassName, new CallerUsage(callerClassName));
					}
				}
			}
			this.usageMap.get(callerClassName).addUsage(usageTime);
		}
		public Set<CallerUsage> getUsages() {
			return new HashSet<CallerUsage>(this.usageMap.values());
		}
		@Override
		public String toString() {
			return "[Usage Statistics: "+getUsages()+"]";
		}

		private class CallerUsage {

			private final String callerClassName;
			private int calls = 0;
			private long maxTime = 0;
			private long mediumTime = 0;
			private long totalTime = 0;

			public CallerUsage(final String callerClassName) {
				this.callerClassName = callerClassName;
			}

			public synchronized void addUsage(final long usageTime) {
				this.maxTime = Math.max(this.maxTime,usageTime);
				this.calls++;
				this.totalTime += usageTime;
				this.mediumTime = this.totalTime / this.calls;
			}

			@Override
			public String toString() {
				return "[Usage: caller="+this.callerClassName+", calls="+this.calls+", totalTime="+this.totalTime+", maxTime="+this.maxTime+", mediumTime="+this.mediumTime+"]";
			}

			@Override
			public int hashCode() {
				return this.callerClassName.hashCode();
			}
		}
	}

	private String getCallerClassName() {
		final Throwable t = new Throwable();
		final StackTraceElement[] stArray = t.getStackTrace();
		int i=0;
		while (i < stArray.length) {
			final StackTraceElement st = stArray[i++];
			final String className = st.getClassName();
			if (!isExcludedClass(className)) {
				return className;
			}
		}
		return stArray.length > 0 ? stArray[stArray.length-1].getClassName() : null;
	}

	protected boolean isExcludedClass(final String className) {
		final String[] excludedClassPrefixes = new String[] {"com.parallel", "java,sun","$Proxy"};
		for (int i = 0; i < excludedClassPrefixes.length; i++) {
			if (className.startsWith(excludedClassPrefixes[i])) {
				return true;
			}
		}
		return false;
	}
}

