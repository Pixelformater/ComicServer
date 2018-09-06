package org.pixelformater.comicServer.Model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public abstract class GenericModel implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
