package com.fpl.hibernate;

public interface HibernateTemplate {

  <EntityType,ExecutionReturnType> ExecutionReturnType execute(HibernateCallback<EntityType,ExecutionReturnType> hibernateCallback);
  
}
