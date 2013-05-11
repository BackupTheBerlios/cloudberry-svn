package org.berrycloud.core.db.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionConfigurationFactory {

	
	public static SessionFactory sessionFactory;
	
	
	private String packageName="org.berrycloud.model.entity";
	private String userName="SA";
	private String password="";
	private String c3p0MinSize="2";
	private String c3p0MaxSize="20";
	private String c3p0TimeOut="120";
	private String c3p0MaxStatement="50";
	private String c3p0IdlePeriod="3000";
	private String defautSchema="berry";
	private String showSql="true";
	private String formatSql = "true";
	private String dialect = "org.hibernate.dialect.HSQLDialect";
	private String dataSource = "jdbc:hsqldb:hsql://192.168.1.20/xdb";

	public SessionFactory build() throws MappingException,
			ClassNotFoundException, IOException {
		Configuration cfg = new Configuration();

		for (@SuppressWarnings("rawtypes")
		Class clazz : getEntityClasses(packageName)) {
			cfg.addAnnotatedClass(clazz);
		}
		Properties pr = new Properties();

		pr.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		pr.setProperty("hibernate.connection.url", dataSource);
		pr.setProperty("hibernate.connection.password", password);
		pr.setProperty("hibernate.connection.username", userName);
		pr.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		pr.setProperty("hibernate.dialect", dialect);
		pr.setProperty("hibernate.show_sql", showSql);
		pr.setProperty("hibernate.format_sql", formatSql);
		pr.setProperty("hibernate.default_schema", defautSchema);
		pr.setProperty("hibernate.generate_statistics", "true");

		pr.setProperty("hibernate.c3p0.idle_test_period", c3p0IdlePeriod);
		pr.setProperty("hibernate.c3p0.min_size", c3p0MinSize);
		pr.setProperty("hibernate.c3p0.max_size", c3p0MaxSize);
		pr.setProperty("hibernate.c3p0.timeout", c3p0TimeOut);
		pr.setProperty("hibernate.c3p0.max_statements", c3p0MaxStatement);

		cfg.addProperties(pr);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.buildServiceRegistry();
		return cfg.buildSessionFactory();
	}

	
	
	private Collection<Class> getEntityClasses(String packageName)
			throws IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		assert packageName != null;

		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();

			dirs.add(new File(resource.getFile()));
		}

		@SuppressWarnings("rawtypes")
		Collection<Class> classes = new ArrayList<Class>();

		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;

	}
	
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
