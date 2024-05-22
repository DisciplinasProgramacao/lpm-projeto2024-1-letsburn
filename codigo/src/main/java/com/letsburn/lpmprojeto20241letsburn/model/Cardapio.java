package com.letsburn.lpmprojeto20241letsburn.model;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cardapios")
public class Cardapio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cardapio")
    public List<ItemCardapio> itensCardapio;

    public Cardapio() {
    }

    public Long getId() {
        return id;
    }

    public List<ItemCardapio> getItensCardapio() {
        return itensCardapio;
    }

    public void setItensCardapio(List<ItemCardapio> itensCardapio) {
        this.itensCardapio = itensCardapio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((itensCardapio == null) ? 0 : itensCardapio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cardapio other = (Cardapio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (itensCardapio == null) {
            if (other.itensCardapio != null)
                return false;
        } else if (!itensCardapio.equals(other.itensCardapio))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cardapio [id=" + id + ", itensCardapio=" + itensCardapio + "]";
    }

}
