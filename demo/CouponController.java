package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CouponController {

    private static List<Coupon> m_coupons = new ArrayList<>();

    // static ctor
    static {
        m_coupons.add( new Coupon(1, "Caffe") );
        m_coupons.add( new Coupon(2, "Movie VIP") );
        m_coupons.add( new Coupon(3, "Sky jump") );
    }

    @GetMapping("/coupon")
    public List<Coupon> getCoupons()
    {
        return m_coupons;
    }

    @GetMapping("/coupon/{id}")
    public Coupon doGetCouponById(@PathVariable("id") int id)
    {
        for(Coupon c : m_coupons)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }
        return null;
    }

    @PostMapping("/coupon")
    public String addCoupon(@RequestBody Coupon c)
    {
        m_coupons.add(c);
        return "Coupon add";
    }

    @PutMapping("/coupon/{id}")
    public void updateCoupon(@PathVariable("id") int id, @RequestBody Coupon update_c)
    {
        for(Coupon c : m_coupons)
        {
            if (c.getId() == id)
            {
                c.setId(update_c.getId());
                c.setTitle(update_c.getTitle());
                return;
            }
        }
    }

    @DeleteMapping("/coupon/{id}")
    public void delCouponById(@PathVariable("id") int id)
    {
        Iterator<Coupon> iter = m_coupons.iterator();
        while (iter.hasNext()) {
            Coupon c = iter.next();
            if (c.getId() == id) {
                iter.remove();
                return;
            }
        }
    }

}
