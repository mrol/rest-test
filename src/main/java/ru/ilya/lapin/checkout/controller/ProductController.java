package ru.ilya.lapin.checkout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ilya.lapin.checkout.dao.ProductDao;
import ru.ilya.lapin.checkout.filter.DoubleFilter;
import ru.ilya.lapin.checkout.filter.EqualsFilter;
import ru.ilya.lapin.checkout.helper.ParseIntHelper;
import ru.ilya.lapin.checkout.model.Product;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource(name = "productDao")
    private ProductDao productDao;

    @RequestMapping(value = {"/rest/product/", "/rest/product/"}, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProductFiltered(@RequestParam Map<String, String> allRequestParams) {
        logger.info("Start getProductsFiltered.");

        return productDao.getProducts(
                ParseIntHelper.parse(allRequestParams.get("offset")),
                ParseIntHelper.parse(allRequestParams.get("limit")),
                new EqualsFilter("code", allRequestParams.get("code")),
                new DoubleFilter("value", DoubleFilter.CompareMode.FROM, allRequestParams.get("valueFrom")),
                new DoubleFilter("value", DoubleFilter.CompareMode.TO, allRequestParams.get("valueTo"))
        );
    }
}
