// InheritGood.java
//
// Similar to the inheritBad example except that our functionality
// is defined in interfaces. Because no method bodies are given, there
// is no conflict if a class inherits from multiple interfaces. 

// here are the methods that a Novel object should implement
interface Novel2 {
	public int getWordCount();
	public void setWordCount(int wordCount);
	public int getPageCount();
	public void setPageCount(int pageCount);
}

//here are the methods that a Photo Album object should implement
interface PhotoAlbum2 {
	public int getPictureCount();
	public void setPictureCount(int wordCount);
	public int getPageCount();
	public void setPageCount(int pageCount);
}


// the comic book class now implements both interfaces. It only
// needs to provide one version of the getPageCount and setPageCount 
// methods - this will satisfy both interfaces
class ComicBook2 implements Novel2, PhotoAlbum2{
	
	private int wordCount, pictureCount, pageCount;
	
	public ComicBook2(int wordCount, int pictureCount, int pageCount){
		this.wordCount = wordCount;
		this.pictureCount = pictureCount;
		this.pageCount = pageCount;
	}
	
	public int getWordCount(){
		return wordCount;
	}
	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
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


// the test class
public class InheritGood 
{
	public static void main(String[] args) 
	{
		ComicBook2 comic = new ComicBook2(25, 200, 50);
		
		System.out.println("Word Count = " + comic.getWordCount());
		System.out.println("Picture Count = " + comic.getPictureCount());
		System.out.println("Page Count = " + comic.getPageCount());
	}
}

