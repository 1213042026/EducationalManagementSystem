package cn.edu.management.comm;

public class Page {

	private int pageNow = 1;//当前页码
	private int pageCount;//总共有多少页码
	private int maxRowCount;//总记录数
	private int pageSize = 10;//每页显示多少条数据，这是默认的条数
	public Page(int pageSize,int maxRowCount){
		 this.pageSize=pageSize;
		 this.maxRowCount=maxRowCount;
		 maxPage();
	}
	
	
	//计算总页码
	private void maxPage(){
		
		if( maxRowCount % pageSize == 0 ){
			
			pageCount= (int) (maxRowCount / pageSize);
		}else{
			
			pageCount= (int) (maxRowCount / pageSize + 1);
		}
	}
	
	
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public long getMaxRowCount() {
		return maxRowCount;
	}


	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}
	
	
	
	
	
	
}
