package com.firenay.leave;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description
 * @Date: 2020/5/11 19:06
 */
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer id;

	/**
	 * 父节点的id
	 */
	private Integer pid;

    private String name;

	/**
	 * 节点附带的url地址
	 */
	private String url;

	/**
	 * 图标的样式
	 */
    private String icon;

	/**
	 * 存储子节点的集合 初始化避免空指针异常
	 */
	private List<Menu> children = new ArrayList<>();

	/**
	 * 默认打开 zTree
	 */
	private Boolean open = true;

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", pid=" + pid +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", icon='" + icon + '\'' +
				", children=" + children +
				", open=" + open +
				'}';
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}