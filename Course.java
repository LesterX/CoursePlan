public class Course 
{
	String name;
	char code;
	Session ss[] = new Session[10];
	int ss_num = 0;
	
	public Course()
	{
		name = "";
	}
	
	public Course(String n)
	{
		char last = n.charAt(n.length() - 1);
		if (!Character.isDigit(last))
		{
			code = last;
			name = n.substring(0, n.length() - 1);
		}else
			name = n;
	}
	
	public void add_session(Session sess)
	{
		if (ss_num == 10)
			return;
		ss[ss_num] = sess;
		ss_num ++;
	}
	
	public void delete_session(int index)
	{
		for (int i = index - 1; i < ss_num - 1; i ++)
			ss[i] = ss[i + 1];
		
		ss[ss_num - 1] = null;
	}
	
	public void show_session()
	{
		System.out.println(name);
		System.out.println();
		for (int i = 0; i < ss_num; i ++)
		{
			Session sess = ss[i];
			
			if (sess != null)
			{
				System.out.println("    Session " + (i + 1));
				for (Time ti : sess.t)
				{
					if (ti != null)
					{
						System.out.println("        " + ti.day + "  " + ti.start + " ~ " + ti.end);
					}
				}
			}
		}
		System.out.println();
	}
}
