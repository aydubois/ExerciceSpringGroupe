package org.aydbs.projectitems.dao;

import org.aydbs.projectitems.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
