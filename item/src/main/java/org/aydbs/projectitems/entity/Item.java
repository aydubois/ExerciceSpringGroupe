package org.aydbs.projectitems.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Item {
    private @Id
    @GeneratedValue() Long id;

    private @Column
    String name;

    private @Column String region;

    private @Column String regionCode;

    private @Column Long orderId;

    public Item(String name, String regionCode, String region){
        this.name = name;
        this.regionCode = regionCode;
        this.region = region;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Item))
            return false;
        Item item = (Item) obj;
        return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name)
                && Objects.equals(this.regionCode, item.regionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.regionCode);
    }

    @Override
    public String toString() {
        return  "Item{id="+this.id+", name="+this.name+", regionCode="+this.regionCode+"}";
    }
}


