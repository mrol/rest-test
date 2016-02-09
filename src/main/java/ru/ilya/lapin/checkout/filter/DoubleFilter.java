package ru.ilya.lapin.checkout.filter;

import org.hibernate.criterion.DetachedCriteria;
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
public class DoubleFilter implements Filter {

    public enum CompareMode {
        FROM, TO
    }

    private String propertyName;
    private Double value;
    private CompareMode compareMode;

    public DoubleFilter(String propertyName, CompareMode compareMode, String value) {
        if (value != null) {
            try {
                this.value = Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                this.value = null;
            }
            this.propertyName = propertyName;
            this.compareMode = compareMode;
        }
    }


    public DoubleFilter(String propertyName, CompareMode compareMode, Double value) {
        this.propertyName = propertyName;
        this.value = value;
        this.compareMode = compareMode;
    }

    public void setCriteriaFilter(DetachedCriteria criteria) {
        if (value != null) {
            if (CompareMode.FROM.equals(compareMode)) {
                criteria.add(Restrictions.gt(propertyName, value));
            } else {
                criteria.add(Restrictions.lt(propertyName, value));
            }
        }
    }
}
