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

import org.joda.time.DateTime;
import org.joda.time.Period;

public class DeltaTimeElements
{
	public int seconds;
	public int minutes;
	public int hours;
	public int days;
	public int weeks;
	public int months;
	public int years;

	public DeltaTimeElements(DateTime inStartDate, DateTime inEndDate)
	{
		this.DetermineTimeElements(inStartDate, inEndDate);
	}

	public DeltaTimeElements(DateTime inStartDate)
	{
		DateTime endDate = new DateTime();

		this.DetermineTimeElements(inStartDate, endDate);
	}

	public DeltaTimeElements(Date inStartDate)
	{
		DateTime endDate = new DateTime();
		DateTime startDate = new DateTime(inStartDate);

		this.DetermineTimeElements(startDate, endDate);
	}

	public void DetermineTimeElements(DateTime inStartDate, DateTime inEndDate)
	{
		Period deltaTimePeriod = this.ConvertToDelta(inStartDate, inEndDate);

		this.seconds = deltaTimePeriod.getSeconds();
		this.minutes = deltaTimePeriod.getMinutes();
		this.hours = deltaTimePeriod.getHours();
		this.days = deltaTimePeriod.getDays();
		this.weeks = deltaTimePeriod.getWeeks();
		this.months = deltaTimePeriod.getMonths();
		this.years = deltaTimePeriod.getYears();

		return;
	}

	public Period ConvertToDelta(DateTime inStartDate, DateTime inEndDate)
	{
		Period deltaTimePeriod = new Period(inStartDate, inEndDate);

		return deltaTimePeriod;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public int getMinutes()
	{
		return minutes;
	}

	public int getHours()
	{
		return hours;
	}

	public int getDays()
	{
		return days;
	}

	public int getWeeks()
	{
		return weeks;
	}

	public int getMonths()
	{
		return months;
	}

	public int getYears()
	{
		return years;
	}

	public String getDateString()
	{
		String dateString = "";

		dateString = Integer.toString(this.getYears()) +  " " +
			Integer.toString(this.getMonths()) +  " " +
			Integer.toString(this.getWeeks()) +  " " +
			Integer.toString(this.getDays()) +  " " +
			Integer.toString(this.getHours()) +  " " +
			Integer.toString(this.getMinutes()) +  " " +
			Integer.toString(this.getSeconds());

		return dateString;
	}

}
