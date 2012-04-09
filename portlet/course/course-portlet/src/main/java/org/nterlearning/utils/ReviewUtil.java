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


package org.nterlearning.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;
import org.nterlearning.datamodel.catalog.model.Course;

import java.util.List;

/**
 * This class contains methods helpful in reviewing and scoring content.
 *
 * @author bblonski
 */
public class ReviewUtil {

	private static double topReviewerThreshold;

	public static double getTopReviewerThreshold() {
		if(topReviewerThreshold == 0.0) {
			reloadTopReviewerThreshold();
		}
		return topReviewerThreshold;
	}

	public static void reloadTopReviewerThreshold() {
		topReviewerThreshold =
                GetterUtil.getDouble(
                        PropsUtil.get(PortalProperties.TRUSTED_REVIEWER_PROPERTY), 0.9);
	}

	/**
	 * Calculates the lower bound of the Wilson score confidence interval for
	 * Bernoulli parameters. This is very useful for sorting thumbs up/down
	 * types of reviews.
	 *
	 * @param positive
	 *            Number of positive reviews.
	 * @param n
	 *            Total number of reviews.
	 * @param power
	 *            Statistical power. Good example values are 0.05 or 0.1
	 * @return Weighted ranking score of the item.
	 */
	public static double wilsonScore(int positive, int n, double power) {

		if (n == 0) {
			return 0;
		}
		double z = ReviewUtil.pnormaldist(1 - power / 2);
		double phat = 1.0 * positive / n;
		return (phat + z * z / (2 * n) - z *
			Math.sqrt((phat * (1 - phat) + z * z / (4 * n)) / n)) /
			(1 + z * z / n);
	}

	/**
	 * Calculates the Bayesian Average of an item in an arbitrary rating system.
	 * ie. a 5-star rating system. This will return a weighted score that takes
	 * into account the average number of ratings and average score on each
	 * similar item.
	 *
	 * @param score
	 *            Average rating of the item.
	 * @param total
	 *            Total number of ratings of the item.
	 * @param className
	 *            ClassName of the item.
	 * @return Weighted ranking score of the item.
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public static double bayesianAverage(
		double score, int total, String className)
		throws SystemException {

		int avgVotes = 0;
		double avgRating = 0;
		// Get Liferay class loader
		ClassLoader cl = PortalClassLoaderUtil.getClassLoader();
		// Get Ratings Stats for all items of the given classname
		DynamicQuery query =
			DynamicQueryFactoryUtil
                    .forClass(RatingsStats.class, cl)
                    .add(PropertyFactoryUtil.forName("totalEntries").gt(0))
                    .add(PropertyFactoryUtil.forName("classNameId").eq(
                            ClassNameLocalServiceUtil.getClassNameId(className)));
		List<RatingsStats> results =
			RatingsStatsLocalServiceUtil.dynamicQuery(query);
		// Return 0 if no course ratings are found.
		if (results.size() == 0)
			return 0;
		// Average rating must be weighted by number of votes on each entry
		for (RatingsStats stats : results) {
			// add total votes together
			avgVotes += stats.getTotalEntries();
			// add total ratings together
			avgRating += stats.getAverageScore();
		}
		// divide by number of results to get average rating per item.
		avgRating /= results.size();
		// divide by number of results to get average votes per item
		avgVotes /= results.size();
		// return Bayesian Average
		return ((avgVotes * avgRating) + (score * total)) / (avgVotes + total);
	}

	private static double pnormaldist(double qn) {

		double b[] =
			{
				1.570796288, 0.03706987906, -0.8364353589e-3, -0.2250947176e-3,
				0.6841218299e-5, 0.5824238515e-5, -0.104527497e-5,
				0.8360937017e-7, -0.3231081277e-8, 0.3657763036e-10,
				0.6936233982e-12
			};
		if (qn < 0.0 || 1.0 < qn)
			return 0.0;
		if (qn == 0.5)
			return 0.0;
		double w1 = qn;
		if (qn > 0.5)
			w1 = 1.0 - w1;
		double w3 = -Math.log(4.0 * w1 * (1.0 - w1));
		w1 = b[0];
		for (int i = 1; i < 11; i++) {
			w1 += b[i] * Math.pow(w3, i);
		}
		if (qn > 0.5)
			return Math.sqrt(w1 * w3);
		return -Math.sqrt(w1 * w3);
	}

	public static boolean isTopReviewer(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            return (Long)user.getExpandoBridge().getAttribute(
                    ExpandoConstants.REPUTATION_SCORE) >= getTopReviewerThreshold();
        }
        catch (Exception  e) {
            return false;
        }
	}

    public static double getCourseAverageRating(Course course) {
        double numerator = (course.getOneStarRateCount() + course.getTwoStarRateCount() * 2 + course.getThreeStarRateCount() * 3
                + course.getFourStarRateCount() * 4 + course.getFiveStarRateCount() * 5);
        double denominator = (course.getOneStarRateCount()
                + course.getTwoStarRateCount() + course.getThreeStarRateCount() + course.getFourStarRateCount()
                + course.getFiveStarRateCount());
        if(denominator == 0) {
            return 0;
        }
        return numerator/denominator;
    }
	
	public static double getCourseRatingCount(Course course) {
        return (course.getOneStarRateCount()
                + course.getTwoStarRateCount() + course.getThreeStarRateCount() + course.getFourStarRateCount()
                + course.getFiveStarRateCount());
	}

}