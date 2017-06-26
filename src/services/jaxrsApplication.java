package services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class jaxrsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public void ShoppingApplication() {
		singletons.add(new CustomerResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
