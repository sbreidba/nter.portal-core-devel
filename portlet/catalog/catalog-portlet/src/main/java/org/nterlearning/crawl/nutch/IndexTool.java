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

package org.nterlearning.crawl.nutch;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.nterlearning.crawl.nutch.indexer.IndexServer;
import org.nterlearning.crawl.nutch.indexer.IndexServerFactory;
import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Course;
import org.apache.hadoop.conf.Configuration;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * This class is designed to interface between the Nutch CrawlTool and the
 * actual index engine.  It's purpose is to pull general index code out of the
 * CrawlTool class.
 */
public class IndexTool {

    private static IndexTool mInstance = new IndexTool();
    private static IndexServer mIndexer = null;

    private static final Log mLog = LogFactoryUtil.getLog(IndexTool.class);

    private IndexTool() {
    }

    public static IndexTool getInstance() {
        return mInstance;
    }

    public void setIndexer(String indexerName, Configuration config) {
        mIndexer = IndexServerFactory.getIndexer(indexerName, config);
    }


    public void index() {
        if (mIndexer != null) {
            mIndexer.index();
        }
        else {
            mLog.error("Index : Indexer not correctly initialized.");
        }
    }


    public void purgeIndex() {
        if (mIndexer != null) {
            mIndexer.purgeIndex();
        }
        else {
            mLog.error("PurgeIndex : Indexer not correctly initialized.");
        }
    }


    /**
     * Removes a course from the current index.
     *
     * @param course The course to remove.
     */
    public void removeCourseFromIndex(Course course) {

        if (mIndexer == null) {
            mLog.error("RemoveCourseFromIndex : Indexer not correctly initialized.");
            return;
        }

        Query queries[] =
                {(new TermQuery(new Term(NutchConstants.CLASS_INDEX_TAG,
                        QueryParser.escape(Course.class.getName())))),
                 (new TermQuery(new Term(NutchConstants.IRI_INDEX_TAG,
                         QueryParser.escape(course.getCourseIri()))))};

        mIndexer.removeFromIndex(queries);
    }


    /**
     * Removes a course-component from the current index.
     *
     * @param component The component to remove.
     */
    public void removeCourseComponentFromIndex(Component component) {

        if (mIndexer == null) {
            mLog.error("RemoveCourseComponentFromIndex : Indexer not correctly initialized.");
            return;
        }

        Query queries[] =
                {(new TermQuery(new Term(NutchConstants.CLASS_INDEX_TAG,
                        QueryParser.escape(Component.class.getName())))),
                 (new TermQuery(new Term(NutchConstants.IRI_INDEX_TAG,
                         QueryParser.escape(component.getComponentIri()))))};

        mIndexer.removeFromIndex(queries);
    }


    /**
     * Remove all references to a particular FeedReference from the index.
     *
     * @param feedRefIri The IRI of the Feed Reference object to remove.
     */
    public void removeFeedReferenceFromIndex(String feedRefIri) {

        if (mIndexer == null) {
            mLog.error("RemoveFeedReferenceFromIndex : Indexer not correctly initialized.");
            return;
        }

        Query queries[] = {(new TermQuery(new Term("feed_iri",
                                QueryParser.escape(feedRefIri))))};

        mIndexer.removeFromIndex(queries);
    }
}
