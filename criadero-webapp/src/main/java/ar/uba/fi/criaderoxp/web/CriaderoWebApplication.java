package ar.uba.fi.criaderoxp.web;

import java.net.MalformedURLException;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.security.hive.HiveMind;
import org.apache.wicket.security.hive.config.PolicyFileHiveFactory;
import org.apache.wicket.security.hive.config.SwarmPolicyFileHiveFactory;
import org.apache.wicket.security.swarm.SwarmWebApplication;

import ar.uba.fi.criaderoxp.domain.util.Context;

public class CriaderoWebApplication extends SwarmWebApplication {

	@Override
	protected void init() {
		Context.getInstance().setApplicationContext("applicationContext.xml");
		super.init();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO (mmazzei) - return Index.class
		return HelloWorld.class;
	}

	@Override
	public Class<? extends Page> getLoginPage() {
		// TODO Auto-generated method stub
		return SigninPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new CriaderoSession(this, request);
	}

	@Override
	protected Object getHiveKey() {
		return getServletContext().getServletContextName();
	}

	@Override
	protected void setUpHive() {
		PolicyFileHiveFactory factory = new SwarmPolicyFileHiveFactory(getActionFactory());

		try {
			// this example uses 1 policy file but you can add as many as you like
			factory.addPolicyFile(getServletContext().getResource("/WEB-INF/policy.hive"));
		}
		catch (MalformedURLException e) {
			throw new WicketRuntimeException(e);
		}
		// register factory
		// note we are not checking if a hive already exist because this
		// app will only be deployed once
		HiveMind.registerHive(getHiveKey(), factory);

	}

}
