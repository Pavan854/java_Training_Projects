package library;

public class Librarian {
	
	private int librarianId;
	private String librarianName;
	
	
	Librarian(int librarianId,String librarianName){
		this.librarianId=librarianId;
		this.librarianName=librarianName;
	}
	
	@Override
	public String toString() {
		return "LibrarianID "+librarianId+" Librarian Name "+librarianName;
	}
	
	public int getLibrarianId() {
		return librarianId;
	}
	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}
	public String getLibrarianName() {
		return librarianName;
	}
	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
	}
	
	
	

}
