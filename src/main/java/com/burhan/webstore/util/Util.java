package com.burhan.webstore.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.repository.CategoryTree;

@Component
public class Util {

	private List<Category> categories;

	public CategoryTree convertToTree(Category rootCategory, List<Category> childs) {
		CategoryTree categoryTree;
		categoryTree = new CategoryTree(rootCategory.getCategoryId(),rootCategory.getName(), convertToTree(childs),rootCategory.getParentCategory());
		return categoryTree;
	}

	private List<CategoryTree> convertToTree(List<Category> childs) {
		List<CategoryTree> catTrees = new ArrayList<CategoryTree>();
		for (Category child : childs) {
			List<Category> childsOfChild = findChilds(child);
			if (childsOfChild.size() > 0) {
				CategoryTree tree = convertToTree(child, childsOfChild);
				catTrees.add(tree);
			} else {
				CategoryTree categoryTree = new CategoryTree(child.getCategoryId(),child.getName(), null,child.getParentCategory());
				catTrees.add(categoryTree);
			}
		}
		return catTrees;
	}

	// creates trees which has childs
	public List<CategoryTree> createTree(List<Category> categories) {
		this.categories = categories;
		List<CategoryTree> rootWithChilds = new ArrayList<CategoryTree>();
		CategoryTree tree = null;
		Category rootCategory = findRootCategory(categories);
		List<Category> childs = findChilds(rootCategory);
		if (childs.size() > 0) {
			tree = convertToTree(rootCategory, childs);
			rootWithChilds.add(tree);
		} else {
			tree = new CategoryTree(rootCategory.getCategoryId(),rootCategory.getName(), null,rootCategory.getParentCategory());
			rootWithChilds.add(tree);
		}
		return rootWithChilds;
	}

	private Category findRootCategory(List<Category> categories2) {
		Category root = null;
		for (Category category : categories2) {
			if (category.getParentCategory().equals("")) {
				root = category;
			}
		}
		return root;
	}

	private List<Category> findChilds(Category category) {
		List<Category> childs = new ArrayList<Category>();
		for (Category category2 : getCategories()) {
			if (category2.getParentCategory().equals(category.getCategoryId())) {
				childs.add(category2);
			}
		}
		return childs;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	 public static ArrayList<String> removeDuplicates(List<String> list) {

			// Store unique items in result.
			ArrayList<String> result = new ArrayList<>();

			// Record encountered Strings in HashSet.
			HashSet<String> set = new HashSet<>();

			// Loop over argument list.
			for (String item : list) {

			    // If String is not in set, add it to the list and the set.
			    if (!set.contains(item)) {
				result.add(item);
				set.add(item);
			    }
			}
			return result;
		    }

}
