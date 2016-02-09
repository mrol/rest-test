package ru.ilya.lapin.checkout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ilya.lapin.checkout.controller.uri.ProductRestURIConstants;
import ru.ilya.lapin.checkout.dao.ProductDao;
import ru.ilya.lapin.checkout.filter.LikeFilter;
import ru.ilya.lapin.checkout.model.Product;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource(name="productDao")
    private ProductDao productDao;

    @RequestMapping(value = ProductRestURIConstants.DUMMY_PRODUCT, method = RequestMethod.GET)
    public @ResponseBody Product getDummyProduct() {
        logger.info("Start getDummyEmployee");
        Product product = new Product();
        product.setId(9999);
        product.setCode("DUMMY_CODE");
        product.setName("Dummy");
        product.setValue(0.5);
        return product;
    }

    @RequestMapping(value = ProductRestURIConstants.GET_PRODUCT, method = RequestMethod.GET)
    public @ResponseBody List<Product> getProductByCode(@PathVariable("code") String code) {
        logger.info("Start getProductByCode. Code = " + code);
        return productDao.getProducts(new LikeFilter("code", code));
    }
}
