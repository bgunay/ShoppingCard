package com.burhan.webstore.domain.repository;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryTree {

	String id;
	String label;
	List<CategoryTree> children;
	String parentId;
	
	public CategoryTree(String id, String label,  List<CategoryTree> children, String parentId) {
		super();
		this.id = id;
		this.label = label;
		this.children = children;
		this.parentId = parentId;
	}

	
	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}

	

	public List<CategoryTree> getChildren() {
		return children;
	}


	public void setChildren(List<CategoryTree> children) {
		this.children = children;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonChilds = "";
		try {
			jsonChilds = mapper.writeValueAsString(children);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{ label:'" + label + "'" +  
				(children.size() >0 ? " , children:" + jsonChilds +"" : "")+ "}";
	}
	
	
}
