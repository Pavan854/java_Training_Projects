package library;

public class Book {
	
	private int bookId;
	private String title;
	private String author;
	private boolean isAvailable;
	
	Book(int bookId,String title,String author){
		this.bookId=bookId;
		this.title=title;
		this.author=author;
		this.isAvailable=true;
	}
	@Override
	public String toString() {
		return "BookId "+bookId+" Title "+title+" Author "+author+" Book Availability "+isAvailable;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	
	

}
