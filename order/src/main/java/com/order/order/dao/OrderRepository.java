package com.order.order.dao;

import com.order.order.entity.Item;
import com.order.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT name FROM orders WHERE idUser = id")
    public List<Order> findByUserId(Long id);

}
