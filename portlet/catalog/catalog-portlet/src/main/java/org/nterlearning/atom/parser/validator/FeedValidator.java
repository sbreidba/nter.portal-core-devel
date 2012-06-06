/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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

package org.nterlearning.atom.parser.validator;

import org.nterlearning.atom.extension.NterExtension;
import org.nterlearning.atom.parser.model.AsObject;
import org.nterlearning.atom.parser.model.NterCategory;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

public interface FeedValidator {

    public void setExtension();

    public void setExtension(NterExtension extension);

    public boolean validate(Feed feed);

    public boolean validateVocabularyEntry(Entry vocabEntry);

    public boolean validateCategory(NterCategory category);

    public boolean validateCourseEntry(Entry courseEntry);

    public boolean validateCourseRecordEntry(Entry courseRecordEntry);

    public boolean validateCourseComponentEntry(Entry courseComponentEntry);

    public boolean validateGlobalReviewEntry(Entry review);

    public boolean validateLocalReviewEntry(Entry review);

    /**
     * Validates parts of entries that are common to both local and global reviews
     *
     * @param review
     * @return
     */
    public boolean validateReviewEntry(Entry review);

    public boolean validateObject(String errorPrefix, AsObject object);
}