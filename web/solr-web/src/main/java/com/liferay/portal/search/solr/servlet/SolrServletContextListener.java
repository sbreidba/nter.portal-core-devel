/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package com.liferay.portal.search.solr.servlet;

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.search.solr.server.LiveServerChecker;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Zsigmond Rab
 */
public class SolrServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public static void registerLiveServerChecker(
		LiveServerChecker liveServerChecker) {

		_liveServerCheckers.add(liveServerChecker);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		for (LiveServerChecker liveServerChecker : _liveServerCheckers) {
			liveServerChecker.shutdown();

			_liveServerCheckers.remove(liveServerChecker);
		}
	}

	@Override
	protected void doPortalInit() {
	}

	private static Set<LiveServerChecker> _liveServerCheckers =
		new ConcurrentHashSet<LiveServerChecker>();

}