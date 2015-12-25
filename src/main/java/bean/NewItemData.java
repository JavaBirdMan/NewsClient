package bean;

import java.util.List;

public class NewItemData {

	public NewData data;
	
	public int retcode;
	
	public class NewData{
		public String countcommenturl;
		public String more;
		public List<News> news;
		public String title;
		public List<Topic> topic;
		public List<Topnews> topnews;
	}
	public class Topnews{
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public int id;
		public String pubdate;
		public String title;
		public String topimage;
		public String type;
		public String url;
	}
	public class News{
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public int id;
		public String listimage;
		public String pubdate;
		public String title;
		public String type;
		public String url;
		
		
	}
	public class Topic{
		public String description;
		public int id;
		public String listimage;
		public int sort;
		public String title;
		public String url;
	}
}
