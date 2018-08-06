package com.cwt.fedrooms.core;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.cwt.fedrooms.core.inter.DateInterface;

@Component
@Service
public class dateFinder implements DateInterface {

	@Reference
	ResourceResolverFactory resolverFactory;

	@Override
	public String dateFinderAndConverter(String currentPathPath) {
		String dateForm = "";
		try {
			ResourceResolver resourceResolver = null;
			Session session=null;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "jqom");
			//Invoke the getServiceResourceResolver method to create a Session instance
			resourceResolver = resolverFactory.getServiceResourceResolver(param);
	            session = resourceResolver.adaptTo(Session.class);
			           
			//resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
			//Session session = resourceResolver.adaptTo(Session.class);
			Node currentPageNode = session.getNode(currentPathPath);
			Calendar cal = null;
			cal = currentPageNode.getProperty("jcr:created").getDate();
			Date dt = cal.getTime();
			dateForm = new SimpleDateFormat("dd.MM.yyyy").format(dt);
		} catch (Exception e) {
			dateForm="Something is wrong..! please go on Home Page";
		}
		return dateForm;
	}

}
