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

import java.util.Date;

import javax.servlet.jsp.PageContext;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

public class DateUtil {

    public static String getFriendlyDate(PageContext pageContext, Date changeDate) {
        DeltaTimeElements dteObj = new DeltaTimeElements(changeDate);
        DeltaTimeLanguage dtlObj = new DeltaTimeLanguage(pageContext);

        String deltaTimeStr = "";
        if (dteObj.getYears() != 0)
        {
             deltaTimeStr = dtlObj.getLangYears(dteObj.getYears());
        }
        else if (dteObj.getMonths() != 0)
        {
             deltaTimeStr = dtlObj.getLangMonths(dteObj.getMonths());
        }
        else if (dteObj.getWeeks() != 0)
        {
             deltaTimeStr = dtlObj.getLangWeeks(dteObj.getWeeks());
        }
        else if (dteObj.getDays() != 0)
        {
             deltaTimeStr = dtlObj.getLangDays(dteObj.getDays());
        }
        else if (dteObj.getHours() != 0)
        {
             deltaTimeStr = dtlObj.getLangHours(dteObj.getHours());
        }
        else if (dteObj.getMinutes() != 0)
        {
             deltaTimeStr = dtlObj.getLangMinutes(dteObj.getMinutes());
        }
        else if (dteObj.getSeconds() != 0)
        {
             deltaTimeStr = dtlObj.getLangSeconds(dteObj.getSeconds());
        }

        return deltaTimeStr;
    }

    public static String getFriendlyDuration(PageContext pageContext, String duration) throws DatatypeConfigurationException {
        DatatypeFactory dtf = DatatypeFactory.newInstance();
        try {
            Duration durationValue = dtf.newDuration(duration);

            DurationLanguage dlObj = new DurationLanguage(pageContext);
            String durationStr = "";

            // if years, months return combination of those values.
            if ((durationValue.getYears() > 0) && (durationValue.getMonths() > 0)) {
                durationStr = dlObj.getLangYearsMonths(durationValue.getYears(), durationValue.getMonths());
            } else if (durationValue.getYears() > 0) {
                durationStr = dlObj.getLangYears(durationValue.getYears());
            } else if (durationValue.getMonths() > 0) {
                durationStr = dlObj.getLangMonths(durationValue.getMonths());
            }

            // if days, hours, minutes return combination of those values.
            if (durationStr.equals("")) {
                if ((durationValue.getDays() > 0) && (durationValue.getHours() > 0) && (durationValue.getMinutes() > 0)) {
                    durationStr = dlObj.getLangDaysHoursMinutes(durationValue.getDays(), durationValue.getHours(), durationValue.getMinutes());
                } else if ((durationValue.getDays() > 0) && (durationValue.getMinutes() > 0)) {
                    durationStr = dlObj.getLangDaysMinutes(durationValue.getDays(), durationValue.getMinutes());
                } else if (durationValue.getDays() > 0) {
                    durationStr = dlObj.getLangDays(durationValue.getDays());
                } else if ((durationValue.getHours() > 0) && (durationValue.getMinutes() > 0)) {
                    durationStr = dlObj.getLangHoursMinutes(durationValue.getHours(), durationValue.getMinutes());
                } else if (durationValue.getHours() > 0) {
                    durationStr = dlObj.getLangHours(durationValue.getHours());
                } else if (durationValue.getMinutes() > 0) {
                    durationStr = dlObj.getLangMinutes(durationValue.getMinutes());
                }
            }

            // if only seconds then return that value only.
            if (durationStr.equals("")) {
                if (durationValue.getSeconds() > 0) durationStr = dlObj.getLangSeconds(durationValue.getSeconds());
            }
            return durationStr;
        } catch (IllegalArgumentException e) {
            throw new DatatypeConfigurationException(
        				"Duration format invalid [" + duration + "]");
        }
    }
}