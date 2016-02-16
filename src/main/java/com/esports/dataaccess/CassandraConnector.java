package com.esports.dataaccess;

import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.esports.service.nosql.impl.PlayersServiceImpl;

import static java.lang.System.out;

/**
 * 
 * Class used for connecting to Cassandra database.
 */

public class CassandraConnector {
	
	
	private static Logger logger = Logger.getLogger(CassandraConnector.class.getName());

	private Cluster cluster;
	private Session session;
	private static CassandraConnector instance = null;

	//TODO - Nk make this a singleton class
	private void connect(final String node, final int port)	{

		this.cluster = Cluster.builder().addContactPoint(node).withPort(port)
				.build();
		final Metadata metadata = cluster.getMetadata();
		out.printf("Connected to cluster: %s\n", metadata.getClusterName());

		for (final Host host : metadata.getAllHosts())	{
			out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
			host.getDatacenter(), host.getAddress(), host.getRack());
		}

		session = cluster.connect();
	}
	
	public static CassandraConnector getInstance() {
						
		if (instance == null || instance.getSession().isClosed()) {
			logger.debug("instance is null or closed");
			instance = new CassandraConnector();
			instance.connect("localhost", 9042);
		}
		logger.info("IS CLOSED? " + instance.getSession().getCluster().isClosed());
		
		return instance;
	}
	
	public Session getSession()	{

		return this.session;
	}
	
	public void close()	{

		cluster.close();
	}

}