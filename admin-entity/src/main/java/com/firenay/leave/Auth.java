package com.firenay.leave;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private Integer id;

    private String name;

    private String title;

    private Integer categoryId;

	@Override
	public String toString() {
		return "Auth{" +
				"id=" + id +
				", name='" + name + '\'' +
				", title='" + title + '\'' +
				", categoryId=" + categoryId +
				'}';
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}