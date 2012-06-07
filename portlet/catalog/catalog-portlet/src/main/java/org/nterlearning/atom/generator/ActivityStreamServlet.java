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

package org.nterlearning.atom.generator;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.model.Feed;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author gjiva
 *
 */
public class ActivityStreamServlet extends HttpServlet{
	
	private static final long serialVersionUID = 123L;
	private static Log log = LogFactoryUtil.getLog(ActivityStreamServlet.class);
	
	public static final String CLIENT_ETAG_PARAM = "If-None-Match";
	public static final String SERVER_ETAG_PARAM = "Etag";
    public static final String LOCAL_REVIEWS_PARAM = "local";


    @Override
    public void init() throws ServletException {
        super.init();
    }


    @Override
    public void destroy() {
        super.destroy();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	try {
			String clientEtag = req.getHeader(CLIENT_ETAG_PARAM);
			
			log.info("Received GET request with ETag: " + clientEtag);

			// get the activity stream
			String serverEtag = createEtag(); // create one now to ensure no loss of data
            String local = req.getParameter(LOCAL_REVIEWS_PARAM);
            Feed as = null;
            if (local != null){
                log.info("local param: " + local); //TODO: debug, remove
                as = NterActivityStreamGenerator.getLocalReviews(req.getRequestURL().toString());
            }
            else {
                as = NterActivityStreamGenerator.getActivityStream(req.getRequestURL().toString(),
                        getTimestampFromEtag(clientEtag));
            }
			
			int status;
			String content;
			String contentType;
			
			// if it's empty, return a 304
			if (as.getEntries().isEmpty()){
				log.info("Activity stream is empty; returning 304 status");
				status = HttpServletResponse.SC_NOT_MODIFIED;
				content = "Activity Stream has not been modified since requested ETag of " + clientEtag;
				contentType = "text/plain";
			}
			// otherwise, return the feed with a new ETag
			else {
				log.info("Returning activity stream with " + as.getEntries().size() + " entries");
				
				// serialize the feed
				StringWriter sw = new StringWriter();
				as.writeTo(AbderaAtomGenerator.getRegularWriter(), sw);
				
				// build the response
				resp.addHeader(SERVER_ETAG_PARAM, serverEtag);
				status = HttpServletResponse.SC_OK;
				content = sw.toString();
				contentType = "application/atom+xml";
				
			}
			
			resp.setStatus(status);
			resp.setContentLength(content.length());
			resp.setContentType(contentType);
			resp.getWriter().print(content);
			resp.getWriter().close();
		}
		catch (Exception e) {
			log.error("Error fulfilling GET request: " + e,e);
		}
    }

    
    /**
     * 
     * @param etag
     * @return
     */
    private long getTimestampFromEtag(String etag){
    	
    	if (etag == null){
    		return -1;
    	}
    	else {
    		try {
				return Long.valueOf(etag);
			}
			catch (NumberFormatException e) {
				log.warn("Error converting incoming request's ETag value of [" + etag + "] into a timestamp");
				log.warn(ExceptionUtils.getFullStackTrace(e));
				return -2;
			}
    	}
    }
    
    
    /**
     * 
     * @return
     */
    private String createEtag(){    	
    	return String.valueOf(System.currentTimeMillis());
    }
}
