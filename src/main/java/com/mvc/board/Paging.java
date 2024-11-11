package com.mvc.board;

public class Paging {
	
	private static final int VIEW_POSTS_NUM = 10; // 한 페이지당 보여지는 게시글 수
	
	private static final int VIEW_PAGE_NUM = 10; // 한 페이지당 보여줄 페이지 수
	// [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [다음]
	// [이전] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [다음]
	// [이전] [21] [22]
	
	private int totalPosts; // 총 게시글 수
	// DB에서 총게시글 수를 얻어와야 한다.
	
	private int currentPage; // 현재 페이지 번호
	// 클라이언트에서 전달된 페이지 번호
	
	private int totalPage; // 게시판 전체 페이지 수 (총 페이지 수)
	private int endPage; // 현재 페이지의 끝번호
	private int startPage; // 현재 페이지의 시작번호
	
	private boolean prev; // 이전버튼 활성화 여부
	private boolean next; // 다음버튼 활성화 여부
	
	public Paging(int totalPosts, int currentPage) {
		
		this.totalPosts = totalPosts;
		this.currentPage = currentPage;
		
		pagingMaker();
		
	}
	
	// 페이지 번호를 만들어주는 메소드
	private void pagingMaker() {
		
		// 총 페이지 수
		// 총게시글 수 : 102 / 한페이지당 보여질 게시글 수 : 10
		totalPage = ((totalPosts -1) / VIEW_POSTS_NUM) + 1;
		
		// 현재 페이지 유효성 검사
		if(currentPage < 1 || currentPage > totalPage) {
			currentPage = 1;
		}
		
		// 끝 페이지 만들기
		// 현재페이지 / 한페이지당 보여질 페이지 수(VIEW_PAGE_NUM)
		// [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [다음]
		// [이전] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [다음]
		// [이전] [21] [22]
		
		endPage = ((currentPage -1) / VIEW_PAGE_NUM + 1) * VIEW_PAGE_NUM;
		
		// 끝 페이지 유효성 검사
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 시작페이지 만들기
		startPage = ((currentPage -1) / VIEW_PAGE_NUM) * VIEW_PAGE_NUM + 1;
		
		
		// [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [다음]
		// [이전] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [다음]
		// [이전] [21] [22]
		
		// 이전버튼, 다음버튼 활성화 여부 만들기
		prev = (startPage == 1) ? false : true;
		
		next = (endPage == totalPage) ? false : true;
		
	}

	public int getTotalPosts() {
		return totalPosts;
	}

	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	
	
}





