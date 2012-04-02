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
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * This class contains routines useful to the Nutch Crawler
 */
public class CrawlUtil {

    private static final Log mLog = LogFactoryUtil.getLog(CrawlUtil.class);

    /**
     * Returns a collection of sub directories as <code>Path</code> objects.
     *
     * @param parentDir The Parent Path to collect sub directories for.
     *
     * @return Array of sub directories
     */
    public static Path[] getSubDirectories(Path parentDir) {
        ArrayList<Path> subDirs = new ArrayList<Path>();

        File segmentDir = new File(parentDir.toString());
        File segmentSubDirs[] = segmentDir.listFiles();

        for (File subDir : segmentSubDirs) {
            subDirs.add(new Path(subDir.toString()));
        }

        Path subDirArray[] = new Path[subDirs.size()];
        return subDirs.toArray(subDirArray);
    }


    /**
     * Finds and returns the Virtual host for the company.  This routine assumes
     * only one company has been configured for this instance of NTER.
     *
     * @return VirualHost
     */
    public static String getCompanyVirtualHost() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            Company company = CompanyLocalServiceUtil.getCompany(companyId);

            return company.getVirtualHostname();
        }
        catch (Exception e) {
            mLog.error("Nutch Crawler: Could not get company virtual Id");
            return "localhost";
        }
    }


    public static String getGuestUrl() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            Company company = CompanyLocalServiceUtil.getCompany(companyId);
            Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, "Guest");

            return company.getVirtualHostname() +
                    PortalUtil.getPathFriendlyURLPublic() + guestGroup.getFriendlyURL();
        }
        catch (Exception e) {
            mLog.error("Nutch Crawler: could not determine default guest url");
            return "localhost/web/guest";
        }
    }


    public static void rmDir(FileSystem fs, Path dir) {
        try {
            if (fs.exists(dir)) {
                fs.delete(dir, true);
            }
        }
        catch (IOException e) {
            mLog.error(e.getMessage());
        }
    }


    public static void rmDir(FileSystem fs, Path[] dirs) {
        for (Path dir : dirs) {
            rmDir(fs, dir);
        }
    }
}