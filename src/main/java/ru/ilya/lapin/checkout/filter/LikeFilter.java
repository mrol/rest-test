package ru.ilya.lapin.checkout.filter;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Created by ilya on 08.02.16.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
public class LikeFilter implements Filter {

    private String propertyName;
    private String value;
    private MatchMode matchMode;

    public LikeFilter(String propertyName, String value) {
        this.propertyName = propertyName;
        this.value = value;
        this.matchMode = MatchMode.ANYWHERE;
    }

    public LikeFilter(String propertyName, String value, MatchMode matchMode) {
        this.propertyName = propertyName;
        this.value = value;
        this.matchMode = matchMode;
    }


    public void setCriteriaFilter(DetachedCriteria criteria) {
        criteria.add(Restrictions.like(propertyName, value, matchMode));
    }
}
