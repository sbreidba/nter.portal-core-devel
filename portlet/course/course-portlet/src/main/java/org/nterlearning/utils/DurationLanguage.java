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
public class DurationLanguage
{
	private PageContext m_context;

	public DurationLanguage(PageContext context)
	{
		this.m_context = context;
	}

    //Years, Months
    public String getLangYearsMonths(int inYears, int inMonths) {
		String phrase = "";
        String[] msg_callout_args = {Integer.toString(inYears), Integer.toString(inMonths)};

		if (this.m_context != null) {
			if ((inYears > 1) && (inMonths > 1)) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-years-months", msg_callout_args );
			} else if ((inYears > 1) && (inMonths == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-years-month-mixed", msg_callout_args );
            } else if ((inYears == 1) && (inMonths > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-year-months-mixed", msg_callout_args );
            } else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-year-month-singular", msg_callout_args);
			}
		}
		return phrase;
	}

	public String getLangYears(int inYears) {
		String phrase = "";

		if (this.m_context != null) {
			if (inYears > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-years", Integer.toString(inYears));
			} else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-year-singular", Integer.toString(inYears));
			}
		}
		return phrase;
	}

	public String getLangMonths(int inMonths) {
		String phrase = "";

		if (this.m_context != null) {
			if (inMonths > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-months", Integer.toString(inMonths));
			} else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-month-singular", Integer.toString(inMonths));
			}
		}
		return phrase;
	}

    //Days, Hours, Minutes
    public String getLangDaysHoursMinutes(int inDays, int inHours, int inMinutes) {
		String phrase = "";
        String[] msg_callout_args = {Integer.toString(inDays), Integer.toString(inHours), Integer.toString(inMinutes)};

		if (this.m_context != null) {
			if ((inDays > 1) && (inHours > 1) && inMinutes > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-days-hours-minutes", msg_callout_args );
			} else if ((inDays > 1) && (inHours == 1) && (inMinutes == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-days-hour-minute-mixed", msg_callout_args );
			} else if ((inDays > 1) && (inHours > 1) && (inMinutes == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-days-hours-minute-mixed", msg_callout_args );
            } else if ((inDays > 1) && (inHours == 1) && (inMinutes > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-days-hour-minutes-mixed", msg_callout_args );
			} else if ((inDays == 1) && (inHours > 1) && (inMinutes > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-day-hours-minutes-mixed", msg_callout_args );
            } else if ((inDays == 1) && (inHours > 1) && (inMinutes == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-day-hours-minute-mixed", msg_callout_args );
            } else if ((inDays == 1) && (inHours == 1) && (inMinutes > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-day-hour-minutes-mixed", msg_callout_args );
            } else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-day-hour-minute-singular", msg_callout_args);
			}
		}
		return phrase;
	}

        public String getLangDaysMinutes(int inDays, int inMinutes) {
		String phrase = "";
        String[] msg_callout_args = {Integer.toString(inDays), Integer.toString(inMinutes)};

		if (this.m_context != null) {
			if ((inDays > 1) && inMinutes > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-days-minutes", msg_callout_args );
			} else if ((inDays > 1) && (inMinutes == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-days-minute-mixed", msg_callout_args );
            } else if ((inDays == 1) && (inMinutes > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-day-minutes-mixed", msg_callout_args );
            } else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-day-minute-singular", msg_callout_args);
			}
		}
		return phrase;
	}

    public String getLangHoursMinutes(int inHours, int inMinutes) {
		String phrase = "";
        String[] msg_callout_args = {Integer.toString(inHours), Integer.toString(inMinutes)};

		if (this.m_context != null) {
			if ((inHours > 1) && inMinutes > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-hours-minutes", msg_callout_args );
			} else if ((inHours > 1) && (inMinutes == 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-hours-minute-mixed", msg_callout_args );
            } else if ((inHours == 1) && (inMinutes > 1)) {
                phrase = LanguageUtil.format(this.m_context, "delta-duration-hour-minutes-mixed", msg_callout_args );
            } else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-hour-minute-singular", msg_callout_args);
			}
		}
		return phrase;
	}

	public String getLangDays(int inDays) {
		String phrase = "";

		if (this.m_context != null) {
			if (inDays > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-days", Integer.toString(inDays));
			} else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-day-singular", Integer.toString(inDays));
			}
		}
		return phrase;
	}

	public String getLangHours(int inHours) {
		String phrase = "";

		if (this.m_context != null) {
			if (inHours > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-hours", Integer.toString(inHours));
			} else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-hour-singular", Integer.toString(inHours));
			}
		}
		return phrase;
	}

	public String getLangMinutes(int inMinutes) {
		String phrase = "";

		if (this.m_context != null) {
			if (inMinutes > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-minutes", Integer.toString(inMinutes));
			} else{
				phrase = LanguageUtil.format(this.m_context, "delta-duration-minute-singular", Integer.toString(inMinutes));
			}
		}
		return phrase;
	}

    //seconds only
    public String getLangSeconds(int inSeconds) {
		String phrase = "";

		if (this.m_context != null) {
			if (inSeconds > 1) {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-seconds", Integer.toString(inSeconds));
			} else {
				phrase = LanguageUtil.format(this.m_context, "delta-duration-second-singular", Integer.toString(inSeconds));
			}
		}
		return phrase;
	}

}