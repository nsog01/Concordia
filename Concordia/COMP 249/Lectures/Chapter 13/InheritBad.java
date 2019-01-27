// InheritBad.java
//
// A more realistic example that shows why you cannot inherit from multiple class.
// Here, we know that a comic book is a little bit like a novel and a 
// little bit like a photo album. However, both the novel and photo album
// classes have getPageCount and setPageCount method - with full
// implementations. Java would not know which one to use - note that the
// setPageCount methods are actually different. So, the comicBook class can
// only inherit from one of them. It then has to implement its own version
// of getPictureCount and setPictureCount, even though this methods are the
// same as the ones in the photoAlbum class. 

// the Novel class
class Novel {
	
	private int wordCount;
	private int pageCount;
	
	public Novel(int wordCount, int pageCount){
		this.wordCount = wordCount;
		this.pageCount = pageCount;
	}
	
	public int getWordCount(){
		return wordCount;
	}
	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
	}
	
	public int getPageCount(){
		return pageCount;
	}
	public void setPageCount(int pageCount){
		this.pageCount = pageCount + 2;
	}
}


// the photoAlbum class - note that it also has getPageCount and 
// setPageCount methods

class PhotoAlbum {
	
	private int pictureCount;
	private int pageCount;
	
	public PhotoAlbum(int pictureCount, int pageCount){
		this.pictureCount = pictureCount;
		this.pageCount = pageCount;
	}
	
	public int getPictureCount(){
		return pictureCount;
	}
	public void setPictureCount(int pictureCount){
		this.pictureCount = pictureCount;
	}
	
	public int getPageCount(){
		return pageCount;
	}
	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}
}


// the comicBook class - can only inherit from one class
class ComicBook extends Novel{
	
	int pictureCount;
	
	public ComicBook(int wordCount, int pageCount, int pictureCount){
		super(wordCount, pageCount);
		this.pictureCount = pictureCount;
	}
	
	public int getPictureCount(){
		return pictureCount;
	}
	public void setPictureCount(int pictureCount){
		this.pictureCount = pictureCount;
	}
	
}


// the main test class
public class InheritBad 
{
	public static void main(String[] args) 
	{
		ComicBook comic = new ComicBook(12, 50, 200);
	}
}
