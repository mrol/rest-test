package ru.ilya.lapin.checkout.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.lapin.checkout.filter.Filter;
import ru.ilya.lapin.checkout.model.Order;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Service("orderDao")
@Transactional

public class OrderDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public Set<Order> getOrders(int offset, int limit, Filter... filters) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class, "order");
        for (Filter filter : filters) {
            filter.setCriteriaFilter(detachedCriteria);
        }
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        if (offset >= 0) {
            criteria.setFirstResult(offset);
        }
        if (limit >= 0) {
            criteria.setMaxResults(limit);
        }
        return new HashSet<>((List<Order>)criteria.list());
    }
}
