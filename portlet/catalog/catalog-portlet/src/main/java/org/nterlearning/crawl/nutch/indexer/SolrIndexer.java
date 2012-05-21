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


package org.nterlearning.crawl.nutch.indexer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.util.Arrays;

import org.nterlearning.crawl.nutch.NutchConstants;
import org.nterlearning.utils.PortalProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.search.Query;
import org.apache.nutch.util.HadoopFSUtil;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;


public class SolrIndexer implements IndexServer {

    private String mSolrUrl;

    // indexer
    org.apache.nutch.indexer.solr.SolrIndexer mSolrIndex;

    // file system
    private FileSystem mFS;
    private Path mCrawlDbDir;
    private Path mLinkDbDir;
    private Path mSegmentDir;

    private Configuration mConfig;

    private static final Log mLog = LogFactoryUtil.getLog(SolrIndexer.class);


    public SolrIndexer(Configuration config) {
        mConfig = config;
        mSolrUrl = config.get(PortalProperties.SOLR_URL_PROPERTY);
        mSolrIndex = new org.apache.nutch.indexer.solr.SolrIndexer(mConfig);

        try {
            mFS = FileSystem.get(config);
            Path nutchDataDir =
                    new Path(config.get(PortalProperties.NUTCH_DATA_DIR_PROPERTY));

            mCrawlDbDir = new Path(nutchDataDir, NutchConstants.NUTCH_CRAWLDB);
            mLinkDbDir = new Path(nutchDataDir, NutchConstants.NUTCH_LINKDB);
            mSegmentDir = new Path(nutchDataDir, NutchConstants.NUTCH_SEGMENTS);
        }
        catch (IOException e) {

        }
    }

    public void index() {
        FileStatus fstats[];

        try {
            fstats = mFS.listStatus(mSegmentDir, HadoopFSUtil.getPassDirectoriesFilter(mFS));
            mSolrIndex.indexSolr(mSolrUrl, mCrawlDbDir, mLinkDbDir,
                                 Arrays.asList(HadoopFSUtil.getPaths(fstats)));
            commitAndOptimize();
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    public void removeFromIndex(Query[] queries) {
        try {
            SolrServer solr =  createSolrServer();
            for (Query query : queries) {
                solr.deleteByQuery(query.toString());
            }

            solr.commit();
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    public void purgeIndex() {
        try {
            SolrServer solr =  createSolrServer();
            solr.deleteByQuery("*:*");
            commitAndOptimize(solr);
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    private void commitAndOptimize(SolrServer server) {
        try {
            server.commit();
            server.optimize();
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    private void commitAndOptimize() {
        try {
            SolrServer solr =  createSolrServer();
            solr.commit();
            solr.optimize();
        }
        catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }


    private SolrServer createSolrServer() throws Exception {

        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());

        if (mConfig.get(NutchConstants.SOLR_AUTH_PROPERTY).equals("true")) {
            String user = mConfig.get(NutchConstants.SOLR_AUTH_USER_PROPERTY);
            String password = mConfig.get(NutchConstants.SOLR_AUTH_PASSWORD_PROPERTY);

            HttpState httpState = httpClient.getState();
            httpState.setCredentials(AuthScope.ANY,
                                     new UsernamePasswordCredentials(user, password));

            httpClient.getParams().setAuthenticationPreemptive(true);
        }

        return new CommonsHttpSolrServer(mSolrUrl, httpClient);
    }
}