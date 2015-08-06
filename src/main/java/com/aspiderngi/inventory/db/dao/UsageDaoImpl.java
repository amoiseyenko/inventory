package com.aspiderngi.inventory.db.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java.util.Date;

import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.UsageEntity;

@Repository
public class UsageDaoImpl extends GenericDaoHibernateImpl<UsageEntity, Long> {
	
	public ArrayList<UsageEntity> get(final Long subscriptionId, final Long position, final Integer count) {

		ArrayList<UsageEntity> result = getCurrentSession().doReturningWork(new ReturningWork<ArrayList<UsageEntity>>() {
			
			@Override
			public ArrayList<UsageEntity> execute(Connection connection) throws SQLException {
				ArrayList<UsageEntity> usageEntites = new ArrayList<UsageEntity>();
				
				CallableStatement proc = null;
				ResultSet results = null;

				try{
					proc = connection.prepareCall("{ ? = call dwh.get_usage(?, ?, ?) }");
					proc.registerOutParameter(1, Types.OTHER);
					proc.setLong(2, subscriptionId);
					proc.setLong(3, position);
					proc.setInt(4, count);
	
					proc.execute();
	
					results = (ResultSet) proc.getObject(1);
					
					while (results.next()) {
						UsageEntity usageEntity = new UsageEntity(results.getLong(1), new Date(results.getTimestamp(2).getTime()), results.getInt(3), results.getLong(4), results.getLong(5), results.getString(6), results.getLong(7));
						
						usageEntites.add(usageEntity);
					}
				} finally {
                    if(proc != null) {
                    	proc.close();
                    }
                    if(results != null) {
                    	results.close();
                    }
                }
				
				return usageEntites;
			}
		});
		
		// TODO: vsapiha: refactor
		// https://github.com/spring-projects/spring-data-examples/issues/44
		// https://github.com/hibernate/hibernate-orm/blob/4.3.7.Final/hibernate-core/src/main/java/org/hibernate/procedure/internal/AbstractParameterRegistrationImpl.java
		
//		ProcedureCall procedure = getCurrentSession()
//						//.getNamedQuery("getUsage");
//						.getNamedProcedureCall("getUsage");
//						//		.registerParameter(subscription_id, type, mode)
//
//		procedure.getParameterRegistration("subscription_id").bindValue(1L);
//		procedure.getParameterRegistration("pointer_id").bindValue(0L);
//		procedure.getParameterRegistration("count").bindValue(3);
//
//		
//		procedure.getOutputs().getCurrent();
		
		// ResultSetOutput rso = (ResultSetOutput) procedure.getOutputs().getCurrent();
		
		return result;
	}
}