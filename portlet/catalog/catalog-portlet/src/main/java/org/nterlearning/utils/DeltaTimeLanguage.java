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

import com.liferay.portal.kernel.language.LanguageUtil;

import javax.servlet.jsp.PageContext;

public class DeltaTimeLanguage
{
	private PageContext m_context;

	public DeltaTimeLanguage(PageContext context)
	{
		this.m_context = context;
	}

	public String getLangYears(int inYears) {
		String phrase = "";

		if (this.m_context != null) {
			if (inYears > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-years", Integer.toString(inYears));
			}
			else if (inYears < 0)
			{
				phrase = LanguageUtil.format(this.m_context, "", "");
			}
            else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-years-singular", Integer.toString(inYears));
			}
		}
		return phrase;
	}

	public String getLangMonths(int inMonths) {
		String phrase = "";

		if (this.m_context != null) {
			if (inMonths > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-months", Integer.toString(inMonths));
			}
            else if (inMonths < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-months-singular", Integer.toString(inMonths));
			}

		}
		return phrase;
	}

	public String getLangWeeks(int inWeeks) {
		String phrase = "";

		if (this.m_context != null) {
			if (inWeeks > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-weeks", Integer.toString(inWeeks));
			}
            else if (inWeeks < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-weeks-singular", Integer.toString(inWeeks));
			}

		}
		return phrase;
	}

	public String getLangDays(int inDays) {
		String phrase = "";

		if (this.m_context != null) {
			if (inDays > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-days", Integer.toString(inDays));
			}
            else if (inDays < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-days-singular", Integer.toString(inDays));
			}

		}
		return phrase;
	}

	public String getLangHours(int inHours) {
		String phrase = "";

		if (this.m_context != null) {
			if (inHours > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-hours", Integer.toString(inHours));
			}
            else if (inHours < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-hours-singular", Integer.toString(inHours));
			}

		}
		return phrase;
	}

	public String getLangMinutes(int inMinutes) {
		String phrase = "";

		if (this.m_context != null) {
			if (inMinutes > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-minutes", Integer.toString(inMinutes));
			}
            else if (inMinutes < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-minutes-singular", Integer.toString(inMinutes));
			}

		}
		return phrase;
	}

	public String getLangSeconds(int inSeconds) {
		String phrase = "";

		if (this.m_context != null) {
			if (inSeconds > 1)
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-seconds", Integer.toString(inSeconds));
			}
            else if (inSeconds < 0)
            {
                phrase = LanguageUtil.format(this.m_context, "", "");
            }
			else
			{
				phrase = LanguageUtil.format(this.m_context, "delta-time-seconds-singular", Integer.toString(inSeconds));
			}

		}
		return phrase;
	}

}