package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
	@Column(length = 20, name = "name")
	private String name;

	public Tag() {

	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag) {
			Tag other = (Tag) obj;
			return name.equals(other.name);
		}
		return false;

	}

	@Override
	public String toString() {
		return "Tag [id=" + getId() + ", name=" + name + "]";
	}

}
