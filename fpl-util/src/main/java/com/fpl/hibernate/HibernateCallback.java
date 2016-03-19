package com.fpl.hibernate;

import org.hibernate.Session;

public abstract class HibernateCallback<EntityType,ExecutionReturnType> {

  private final String description;
  
  public HibernateCallback(final String description) {
    this.description = description;
  }
  
  public abstract ExecutionReturnType doInHibernate(Session session);

  public boolean needRollback(final Throwable e) {
    return true;
  }

  @Override
  public String toString() {
    return "[HibernateCallback: "+this.description+"]";
  }
  
}
