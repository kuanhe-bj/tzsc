package io.renren.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: Pagination
* @Description: Page Object
 */
public class Pagination {

	public List<?> order(List<?> list,int totalCount, int pageSize, int currPage) {
	     int totalPage = (int)Math.ceil((double)totalCount/pageSize);
         if (totalCount <= pageSize) {
    	     return list;
	     } else {
		     List<Object> pageList = new ArrayList<>();
		     if (totalPage == currPage) {
		    	 for (int i = (currPage-1)*pageSize; i < list.size(); i++) {
				      pageList.add(list.get(i));	
				 }
			} else {
				for (int i = 0; i < pageSize; i++) {
				      pageList.add(list.get((currPage - 1) * pageSize + i));	
				 }
			}
		     
		     return pageList;
	     }
		
	}
}
