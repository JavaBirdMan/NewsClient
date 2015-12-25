package bean;

import java.util.List;

public class NewCenter {

	public List<NewItem> data;
	public List<String> extend;
	public int retcode;
	public class NewItem{
		public List<Children> children;
		public int id;
		public String title;
		public String type;
		public String url;
		public String url1;
		public String dayurl;
		public String excurl;
		public String weekurl;
	}
	public class Children{
		public int id;
		public String title;
		public String type;
		public String url;
	}
}
