package org.aydbs.projectitems.dao;

import org.aydbs.projectitems.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByOrderId(Long id);
}
