package ru.ilya.lapin.checkout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ilya.lapin.checkout.dao.OrderDao;
import ru.ilya.lapin.checkout.filter.*;
import ru.ilya.lapin.checkout.helper.ParseIntHelper;
import ru.ilya.lapin.checkout.model.Order;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource(name = "orderDao")
    private OrderDao orderDao;

    @RequestMapping(value = {"/rest/order", "/rest/order/"}, method = RequestMethod.GET)
    public
    @ResponseBody
    Set<Order> getOrdersFiltered(@RequestParam Map<String, String> allRequestParams) {
        logger.info("Start getOrdersFiltered.");

        return orderDao.getOrders(
                ParseIntHelper.parse(allRequestParams.get("offset")),
                ParseIntHelper.parse(allRequestParams.get("limit")),
                new ProductCodeFilter(allRequestParams.get("productCode")),
                new DateFilter("orderDate", CompareMode.FROM, allRequestParams.get("dateFrom")),
                new DateFilter("orderDate", CompareMode.TO, allRequestParams.get("dateTo"))
        );
    }
}
