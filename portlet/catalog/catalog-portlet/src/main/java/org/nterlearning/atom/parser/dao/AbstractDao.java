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


package org.nterlearning.atom.parser.dao;

import com.liferay.portal.kernel.exception.SystemException;
import org.nterlearning.atom.parser.FeedContext;

import java.util.List;


public abstract class AbstractDao<T> {

    private FeedContext feedContext;


    public FeedContext getFeedContext() {
        return feedContext;
    }


    public void setFeedContext(FeedContext feedContext) {
        this.feedContext = feedContext;
    }


    /**
     * Persists insert of an object of this class' data type
     *
     * @param t
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public abstract void persistAdd(T t) throws SystemException;


    /**
     * Persists update of an object of this class' data type
     *
     * @param t
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public abstract void persistUpdate(T t) throws SystemException;


    /**
     * Persists delete of an object of this class' data type
     *
     * @param t
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public abstract void persistDelete(T t) throws SystemException;


    /**
     * Returns some primaryKey for the object passed in
     *
     * @param t
     * @return
     */
    public abstract long getPrimaryKey(T t);


    /**
     * Sets primary key value for the object passed in
     *
     * @param t
     * @param primaryKey
     * @return
     */
    public abstract void setPrimaryKey(T t, long primaryKey);


    /**
     * Returns revert list in event of rollback for parent primary key value
     *
     * @param
     * @param parentPrimaryKey
     * @return
     */
    public abstract List<T> getRevertList(long parentPrimaryKey);


    /**
     * Returns concatenated string of unique column values for the object passed in
     *
     * @param t
     * @return
     */
    public abstract String getContents(T t);


    /**
     * Returns some hopefully unique identifier for the object passed in
     * in the event an exception is thrown for message purposes
     *
     * @param t
     * @return
     */
    public abstract String getId(T t);


    /**
     * Returns a label for the object's class i.e., if T is a class "SUV", it may
     * return "Sports Utility Vehicle"
     * This is used for debug/informational purposes
     *
     * @param t
     * @return
     */
    public abstract String getLabel(T t);

    
    /**
     * @param fromList
     * @param toList
     * @param parentPrimaryKey
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void persistListWithCompareAndRollback(List<T> fromList, List<T> toList, long parentPrimaryKey)
            throws SystemException {

        // persist all the things in the list
        boolean found = false;

        for (int i = 0; i < toList.size(); i++) {

            T toItem = toList.get(i);
            try {
                //compare to determine update or add
                found = false;
                for (int j = 0; j < fromList.size(); j++) {
                    T fromItem = fromList.get(j);
                    if (getContents(toItem).equals(getContents(fromItem))) {
                        found = true;
                        long primaryKey = getPrimaryKey(fromItem);
                        setPrimaryKey(toItem, primaryKey);
                        persistUpdate(toItem);
                        break;
                    }
                }
                if (!found) {
                    persistAdd(toItem);
                }
            }
            catch (Exception e) {
                // if we can't persist one of the items revert the changes to original list
                List<T> revertList = getRevertList(parentPrimaryKey);
                deleteList(revertList);

                for (int j = 0; j < fromList.size(); j++) {
                    T fromItem = fromList.get(j);
                    persistAdd(fromItem);
                }
                //
                throw new SystemException("Problem persisting " + getLabel(toItem) + ": " +
                        getId(toItem) + ": ", e);
            }
        }

        // reverse check for deletes
        for (int j = 0; j < fromList.size(); j++) {

            T fromItem = fromList.get(j);

            try {
                found = false;
                for (int i = 0; i < toList.size(); i++) {
                    T toItem = toList.get(i);
                    if (getContents(toItem).equals(getContents(fromItem))) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    persistDelete(fromItem);
                }
            }
            catch (Exception e) {
                // if we can't persist one of the items,
                // go through the list again and revert the deletes to original list

                List<T> revertList = getRevertList(parentPrimaryKey);
                deleteList(revertList);

                for (int i = 0; j < fromList.size(); j++) {
                    persistAdd(fromItem);
                }
                //
                throw new SystemException("Problem persisting delete of " + getLabel(fromItem) + ": " +
                        getId(fromItem) + ": ", e);
            }
        }
    }


    /**
     * Deletes the contents of the list passed in from persistence
     *
     * @param list
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void deleteList(List<T> list)
            throws SystemException {

        for (T t : list) {
            persistDelete(t);
        }
    }


    /**
     * Adds the contents of the list passed in from persistence
     *
     * @param list
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void addList(List<T> list)
            throws SystemException{

		for (T t:list){
			persistAdd(t);
		}
	}
}