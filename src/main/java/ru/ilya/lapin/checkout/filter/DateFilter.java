package ru.ilya.lapin.checkout.filter;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ilya on 10.02.16.
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
public class DateFilter implements Filter {

    private static final String DATETIME_FORMAT = "yyyy-MM-ddHH:mm";

    private String propertyName;
    private CompareMode compareMode;
    private Date date;

    public DateFilter(String propertyName, CompareMode compareMode, String date) {
        if (date != null) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            try {
                this.date = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                this.date = null;
            }


            this.propertyName = propertyName;
            this.compareMode = compareMode;
        }
    }

    public DateFilter(String propertyName, CompareMode compareMode, Date date) {
        this.propertyName = propertyName;
        this.compareMode = compareMode;
        this.date = date;
    }

    @Override
    public void setCriteriaFilter(DetachedCriteria criteria) {
        if (date != null) {
            if (CompareMode.FROM.equals(compareMode)) {
                criteria.add(Restrictions.gt(propertyName, date));
            } else {
                criteria.add(Restrictions.lt(propertyName, date));
            }
        }
    }
}
